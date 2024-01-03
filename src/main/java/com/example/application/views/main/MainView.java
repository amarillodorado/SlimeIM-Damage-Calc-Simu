package com.example.application.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import damagecalculator.*;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Main")
@Route(value = "/MainView")
public class MainView extends VerticalLayout {
    Grid<DamageResult> simulationResultsGrid = new Grid<>(DamageResult.class, false);


    private List<Unit> unitSelected = new ArrayList<>(); // Schritt 2: Liste für ausgewählte Units
    private Grid<Unit> selectedUnitsGrid = new Grid<>(Unit.class);
    public MainView(){
        Simulator simulator = new Simulator();
        simulationResultsGrid.addColumn(DamageResult::getUnitNames).setHeader("Unit Names").setSortable(true);
        simulationResultsGrid.addColumn(DamageResult::getDamage).setHeader("Damage").setSortable(true);

        HorizontalLayout top = new HorizontalLayout();
        top.setWidthFull();
        top.setAlignItems(Alignment.CENTER);
        top.setJustifyContentMode(JustifyContentMode.CENTER);

        top.add(new Span("Slime Isekai Memories Damage Calculator & Simulator"));

        HorizontalLayout bot = new HorizontalLayout();
        bot.setSizeFull();
        VerticalLayout left_bot = new VerticalLayout();
        left_bot.setSizeFull();
        VerticalLayout right_bot = new VerticalLayout();
        right_bot.setSizeFull();
        VerticalLayout mid_bot = new VerticalLayout();
        mid_bot.setSizeFull();

        DamageInput damageInputBase = new DamageInput();
        damageInputBase.initializeDamageDialog(left_bot);
        Button calcDamage = new Button("Calculate Damage");
        calcDamage.addClickListener(event->{
            double ergebnis = damageInputBase.createObject().calculateDamage();
            Dialog showdialog = new Dialog();
            showdialog.add("You do "+ergebnis);
            showdialog.open();
        });

        Accordion simulateDamageAccordion = new Accordion();
        simulateDamageAccordion.close();
        Button simulateDamage = new Button("Simulate Damage");
        Button addUnit = new Button("Add Unit");
        Dialog addUnitDialog = new Dialog();
        TextField name = new TextField("Name");
        VerticalLayout layout_DamageDialog_v = new VerticalLayout();
        VerticalLayout layout_name_v = new VerticalLayout();
        VerticalLayout layout_createUnit_v = new VerticalLayout();
        Button createUnitDialog = new Button("Create Unit");
        ComboBox<String> preUnits = new ComboBox<>();
        Button showDamageInput = new Button("Damage Set");
        IntegerField numberOfCombination = new IntegerField("Number of Combination");
        VerticalLayout layout_simulator_v = new VerticalLayout();
        DamageInput damageInputAddUnit = new DamageInput();

        layout_name_v.setPadding(false);
        layout_name_v.add(name);
        layout_DamageDialog_v.setPadding(false);
        damageInputAddUnit.initializeDamageDialog(layout_DamageDialog_v);

        layout_createUnit_v.add(createUnitDialog);
        addUnitDialog.add(layout_name_v,layout_DamageDialog_v,layout_createUnit_v);

        showDamageInput.addClickListener(event ->{

        });

        createUnitDialog.addClickListener(event->{
            Unit createdUnit = new Unit(name.getValue(), damageInputAddUnit.createObject());
            unitSelected.add(createdUnit);
            updateSelectedUnitsDisplay();
            addUnitDialog.close();
        });

        addUnit.addClickListener(event->{
            addUnitDialog.open();
        });

        preUnits.setItems(UnitPreList.getAllEnumNames());
        preUnits.addValueChangeListener(event -> {
            String selectedName = event.getValue();
            List<Unit> units = UnitPreList.getSpecificUnitByEnumName(selectedName);
            if (!units.isEmpty()) {
                unitSelected.addAll(units);
                updateSelectedUnitsDisplay();
            }
        });

        selectedUnitsGrid.setItems(unitSelected);
        selectedUnitsGrid.setColumns("name"); // Nehmen wir an, Unit hat eine "getName"-Methode
        selectedUnitsGrid.addItemClickListener(event -> {
            Unit unitToRemove = event.getItem();
            unitSelected.remove(unitToRemove);
            updateSelectedUnitsDisplay();
        });

        simulateDamage.addClickListener(event->{
            simulator.setDamageObject(damageInputBase.createObject());
            simulator.combinationHandler(unitSelected,numberOfCombination.getValue());
            List<DamageResult> results = simulator.results;
            simulationResultsGrid.setItems(new ArrayList<DamageResult>());
            simulationResultsGrid.setItems(results);
        });

        layout_simulator_v.add(simulateDamage,addUnit,preUnits,selectedUnitsGrid,showDamageInput,numberOfCombination);
        simulateDamageAccordion.add("Simulator",layout_simulator_v);

        mid_bot.add(calcDamage,simulateDamageAccordion);
        right_bot.add(simulationResultsGrid);
        bot.add(left_bot,mid_bot,right_bot);
        add(top,bot);

    }

    private void updateSelectedUnitsDisplay() {
        selectedUnitsGrid.setItems(unitSelected); // Aktualisieren der Liste
    }

}
