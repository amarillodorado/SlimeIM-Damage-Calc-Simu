package com.example.application.views.main;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import damagecalculator.*;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Calc / Simu")
@Route(value = "")
public class MainView extends VerticalLayout {
    Grid<DamageResult> simulationResultsGrid = new Grid<>(DamageResult.class, false);

    private List<Unit> unitSelected = new ArrayList<>();
    private Grid<Unit> selectedUnitsGrid = new Grid<>(Unit.class);

    private Div noUnitsHint;

    Button simulateDamage = new Button("Simulate Damage");
    Button showDamageInput = new Button("View Units Details");

    public MainView(){
        Simulator simulator = new Simulator();
        simulationResultsGrid.addColumn(DamageResult::getUnitNames).setHeader("Unit Names").setSortable(true);
        simulationResultsGrid.addColumn(DamageResult::getDamage).setHeader("Damage").setSortable(true);

        HorizontalLayout top = new HorizontalLayout();
        top.setWidthFull();
        top.setAlignItems(Alignment.CENTER);
        top.setJustifyContentMode(JustifyContentMode.CENTER);

        Span heading = new Span("Slime Isekai Memories Damage Calculator & Simulator Beta 2.0");
        heading.getStyle().set("font-weight", "bold");
        top.add(heading);

        HorizontalLayout bot = new HorizontalLayout();
        bot.setSizeFull();
        VerticalLayout left_bot = new VerticalLayout();
        left_bot.setSizeFull();
        VerticalLayout right_bot = new VerticalLayout();
        right_bot.setSizeFull();
        VerticalLayout mid_bot = new VerticalLayout();
        mid_bot.setSizeFull();

        DamageInput damageInputBase = new DamageInput();
        left_bot.add(new Span("Your static damage dealing Unit"));
        damageInputBase.initializeDamageDialog(left_bot);
        Button calcDamage = new Button("Calculate Damage");
        calcDamage.addClickListener(event->{
            double ergebnis = damageInputBase.createObject().calculateDamage();
            DamageResult newDamageResult = new DamageResult("Calculated Damage", ergebnis);
            simulationResultsGrid.setItems(newDamageResult);
        });

        Accordion simulateDamageAccordion = new Accordion();
        simulateDamageAccordion.close();
        HorizontalLayout layout_simulateButton_addUnit_h = new HorizontalLayout();

        Button addUnit = new Button("Add Unit");

        TextField name = new TextField("Name");


        ComboBox<String> preUnits = new ComboBox<>();
        preUnits.setLabel("Default Configured Units");
        IntegerField numberOfCombination = new IntegerField("Pairing Size");
        numberOfCombination.setValue(1);
        numberOfCombination.setMin(1);
        addToolTip(numberOfCombination,"e.g. 3 means it combines 3 units from the list until all combinations are done");
        numberOfCombination.setMin(0);
        VerticalLayout layout_simulator_v = new VerticalLayout();
        layout_simulateButton_addUnit_h.add(simulateDamage,addUnit);
        layout_simulateButton_addUnit_h.setPadding(false);

        noUnitsHint = new Div();
        noUnitsHint.setText("No units selected");
        noUnitsHint.getStyle().set("padding", "var(--lumo-size-l)")
                .set("text-align", "center")
                .set("font-style", "italic")
                .set("color", "var(--lumo-contrast-70pct)");
        noUnitsHint.setVisible(false); // Standardmäßig verstecken


        showDamageInput.addClickListener(event -> {
            Dialog dialog = new Dialog();
            dialog.setSizeFull();

            // Die Namen der Schadensarten
            String[] damageTypes = new String[] {
                    // ATK-bezogene Werte
                    "ATK PT",
                    "ATK Buff",
                    "ATK Debuff",

                    // Elementar-Schäden
                    "Elemental ATK Buff",
                    "Elemental ATK Debuff",

                    // Synergie-bezogene Werte
                    "Synergy Active",
                    "Synergy Up Down",
                    "Synergy Partner ATK",

                    // Magie/Physik-Schäden
                    "Magic Physical Buff",
                    "Magic Physical Debuff",

                    /*
                    // Resistenz-bezogene Werte
                    "Attribute Res Buff",
                    "Attribute Res Down",
                    "Attack Res Buff",
                    "Attack Res Down",
                     */

                    // Verteidigung-bezogene Werte
                    "Defence Enemy",
                    "Defence UP",
                    "Defence DOWN",

                    // Spezielle Angriffsarten
                    "Stun Active",
                    "Stun Strike Damage UPDOWN",
                    "Enamor Active",
                    "Enamor Strike Buff",
                    "Critical Active",
                    "Crit Buff AND Debuff",

                    // Spezielle Fähigkeiten
                    "Secret Skill Active",
                    "Secret Skill from Character",
                    "Secret Skill Buff",
                    "Secret Damage Res Down",

                    // Durchdringung und Schwachpunkte
                    "Pierce Active",
                    "Pierce UP Res Down",
                    "Weakpoint Active",
                    "Weakpoint Buff",

                    // Schutz vor Schwächen
                    "Weakness Protector Buff"
                    // ... Fügen Sie hier weitere Gruppierungen hinzu, falls notwendig
            };

            // Liste für DamageTypeValue Objekte
            List<DamageTypeValue> damageTypeValues = new ArrayList<>();

            // Für jede Schadensart ein DamageTypeValue Objekt erzeugen und zur Liste hinzufügen
            for (String damageType : damageTypes) {
                DamageTypeValue damageTypeValue = new DamageTypeValue(damageType);
                for (Unit unit : unitSelected) {
                    Object value = getDamageValueByType(unit.getDamageObject(), damageType);
                    damageTypeValue.addUnitValue(unit.getName(), value);
                }
                damageTypeValues.add(damageTypeValue);
            }

            // Grid initialisieren
            Grid<DamageTypeValue> damageGrid = new Grid<>();
            damageGrid.setItems(damageTypeValues);
            damageGrid.setSizeFull();

            // Fügen Sie eine Spalte für den Schadens-Typ hinzu
            damageGrid.addColumn(DamageTypeValue::getDamageType).setHeader("Damage Type").setAutoWidth(true);

            // Für jede Unit eine Spalte hinzufügen
            for (Unit unit : unitSelected) {
                damageGrid.addColumn(damageTypeValue -> damageTypeValue.getUnitValue(unit.getName()))
                        .setHeader(unit.getName())
                        .setAutoWidth(true);
            }

            dialog.add(damageGrid);
            dialog.open();
        });

        addUnit.addClickListener(event -> {
            // Erstellen Sie jedes Mal einen neuen Dialog, wenn der Button geklickt wird
            Dialog addUnitDialog = new Dialog();
            VerticalLayout layout_name_v = new VerticalLayout();
            VerticalLayout layout_DamageDialog_v = new VerticalLayout();
            VerticalLayout layout_createUnit_v = new VerticalLayout();
            Button createUnitDialog = new Button("Create Unit");

            layout_name_v.setPadding(false);
            layout_name_v.add(name);

            // Erstellen Sie ein neues DamageInput-Objekt für den Dialog
            DamageInput newDamageInput = new DamageInput();
            newDamageInput.initializeDamageDialog(layout_DamageDialog_v);

            layout_createUnit_v.add(createUnitDialog);
            addUnitDialog.add(layout_name_v, layout_DamageDialog_v, layout_createUnit_v);

            createUnitDialog.addClickListener(createEvent -> {
                Unit createdUnit = new Unit(name.getValue(), newDamageInput.createObject());
                addUnitIfNotPresent(createdUnit);
                updateSelectedUnitsDisplay();
                addUnitDialog.close();
            });

            addUnitDialog.open();
        });

        preUnits.setItems(UnitPreList.getAllEnumNames());
        preUnits.addValueChangeListener(event -> {
            String selectedName = event.getValue();
            List<Unit> units = UnitPreList.getSpecificUnitByEnumName(selectedName);
            if (!units.isEmpty()) {
                addUnitIfNotPresent(units.get(0));
                updateSelectedUnitsDisplay();
            }
        });
        selectedUnitsGrid.setAllRowsVisible(true);
        selectedUnitsGrid.setItems(unitSelected);
        selectedUnitsGrid.setColumns("name");


        selectedUnitsGrid.getColumns().forEach(column -> column.setFlexGrow(1));
        selectedUnitsGrid.addComponentColumn(unit -> {
            HorizontalLayout temp = new HorizontalLayout();
            temp.setPadding(false);
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteButton.addClickListener(e -> {
                unitSelected.remove(unit);
                updateSelectedUnitsDisplay();
            });
            Button editButton = new Button(new Icon(VaadinIcon.PENCIL));
            temp.add(editButton,deleteButton);
            return temp;
        }).setFlexGrow(0).setWidth("120px");



        simulateDamage.addClickListener(event->{
            simulator.setDamageObject(damageInputBase.createObject());
            simulator.combinationHandler(unitSelected,numberOfCombination.getValue());
            List<DamageResult> results = simulator.results;
            simulationResultsGrid.setItems(results);
        });

        layout_simulator_v.add(layout_simulateButton_addUnit_h,numberOfCombination,preUnits,noUnitsHint,selectedUnitsGrid,showDamageInput);
        simulateDamageAccordion.add("Simulator",layout_simulator_v);

        updateSelectedUnitsDisplay();
        mid_bot.add(calcDamage,simulateDamageAccordion);
        right_bot.add(simulationResultsGrid);
        bot.add(left_bot,mid_bot,right_bot);
        add(top,bot);

    }

    private Object getDamageValueByType(DamageObject damageObject, String damageType) {
        switch (damageType) {
            // Boolesche Werte werden direkt zurückgegeben
            case "Synergy Active":
                return damageObject.isSynergyTrue();
            case "Stun Active":
                return damageObject.isStunTrue();
            case "Enamor Active":
                return damageObject.isEnamorTrue();
            case "Critical Active":
                return damageObject.isCritTrue();
            case "Secret Skill Active":
                return damageObject.isSecretSkillTrue();
            case "Pierce Active":
                return damageObject.isPierceTrue();
            case "Weakpoint Active":
                return damageObject.isWeakpointTrue();
            // ... Fügen Sie hier zusätzliche Fälle hinzu für andere Checkboxen

            // Numerische Werte werden als Integer zurückgegeben
            case "ATK Debuff":
                return (int)damageObject.getAtk_Debuff();
            case "ATK Buff":
                return (int)damageObject.getAtk_Buff();
            case "ATK PT":
                return (int)damageObject.getAtk_PT();
            case "Elemental ATK Buff":
                return (int)damageObject.getElementalATKBuff();
            case "Elemental ATK Debuff":
                return (int)damageObject.getElementalATKDebuff();
            case "Synergy Up Down":
                return (int)damageObject.getSynergyUpDown();
            case "Synergy Partner ATK":
                return (int)damageObject.getSynergyPartnerATK();
            case "Magic Physical Buff":
                return (int)damageObject.getMagicPhysicalBuff();
            case "Magic Physical Debuff":
                return (int)damageObject.getMagicPhysicalDebuff();
            case "Attribute Res Buff":
                return (int)damageObject.getAttributeResBuff();
            case "Attribute Res Down":
                return (int)damageObject.getAttributeResDown();
            case "Attack Res Buff":
                return (int)damageObject.getAttackResBuff();
            case "Attack Res Down":
                return (int)damageObject.getAttackResDown();
            case "Defence Enemy":
                return (int)damageObject.getDef_ene();
            case "Defence UP":
                return (int)damageObject.getDef_up();
            case "Defence DOWN":
                return (int)damageObject.getDef_down();
            case "Stun Strike Damage UPDOWN":
                return (int)damageObject.getStunStrikeDamageUPDOWN();
            case "Enamor Strike Buff":
                return (int)damageObject.getEnamorStrikeBuff();
            case "Crit Buff AND Debuff":
                return (int)damageObject.getCritBuffANDDebuff();
            case "Secret Skill from Character":
                return (int)damageObject.getSecretSkillfromCharacter();
            case "Secret Skill Buff":
                return (int)damageObject.getSecretSkillBuff();
            case "Secret Damage Res Down":
                return (int)damageObject.getSecretDamageResistanceDown();
            case "Pierce UP Res Down":
                return (int)damageObject.getPierceUPResDown();
            case "Weakness Protector Buff":
                return (int)damageObject.getWeaknessProtectorBuff();
            case "Weakpoint Buff":
                return (int)damageObject.getWeakpointBuff();
            // ... Fügen Sie hier weitere Fälle hinzu, falls Sie weitere Schadensattribute haben
            default:
                return 0;
        }
    }

    public void addUnitIfNotPresent(Unit newUnit) {
        boolean alreadyExists = unitSelected.stream()
                .anyMatch(unit -> unit.getName().equals(newUnit.getName()));

        if (!alreadyExists) {
            unitSelected.add(newUnit);
            updateSelectedUnitsDisplay(); // Ihre Methode zum Aktualisieren der Anzeige
        } else {
            Notification.show("Not allowed to add more than one Unit");
        }
    }

    private void updateSelectedUnitsDisplay() {
            if (unitSelected.isEmpty()) {
                selectedUnitsGrid.setVisible(false);
                noUnitsHint.setVisible(true);
                simulateDamage.setEnabled(false);
                showDamageInput.setEnabled(false);
            } else {
                selectedUnitsGrid.setVisible(true);
                noUnitsHint.setVisible(false);
                selectedUnitsGrid.setItems(unitSelected);
                simulateDamage.setEnabled(true);
                showDamageInput.setEnabled(true);
        }

    }

    private void addToolTip(IntegerField component, String text){
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        component.setSuffixComponent(button);
        component.setTooltipText(text);
        Tooltip tooltip = component.getTooltip().withManual(true);
        button.addClickListener(event -> {
            tooltip.setOpened(!tooltip.isOpened());
        });
    }

}
