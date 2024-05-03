package app.views.novofuncionario;

import app.data.SamplePerson;
import app.services.SamplePersonService;
import app.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("NovoFuncionario")
@Route(value = "my-view6", layout = MainLayout.class)
@Uses(Icon.class)
public class NovoFuncionarioView extends Composite<VerticalLayout> {

    public NovoFuncionarioView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H4 h4 = new H4();
        Hr hr = new Hr();
        FormLayout formLayout3Col = new FormLayout();
        TextField textField = new TextField();
        DatePicker datePicker = new DatePicker();
        TextField textField2 = new TextField();
        FormLayout formLayout2Col = new FormLayout();
        ComboBox comboBox = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        Anchor link = new Anchor();
        Anchor link2 = new Anchor();
        Accordion accordion = new Accordion();
        FormLayout formLayout2Col2 = new FormLayout();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        H5 h5 = new H5();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        FormLayout formLayout2Col3 = new FormLayout();
        ComboBox comboBox3 = new ComboBox();
        TextField textField5 = new TextField();
        Anchor link3 = new Anchor();
        Button buttonSecondary = new Button();
        Grid minimalistGrid = new Grid(SamplePerson.class);
        H5 h52 = new H5();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        FormLayout formLayout2Col4 = new FormLayout();
        ComboBox comboBox4 = new ComboBox();
        ComboBox comboBox5 = new ComboBox();
        Anchor link4 = new Anchor();
        Anchor link5 = new Anchor();
        FormLayout formLayout3Col2 = new FormLayout();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();
        TextField textField8 = new TextField();
        Button buttonSecondary2 = new Button();
        Grid minimalistGrid2 = new Grid(SamplePerson.class);
        ProgressBar progressBar = new ProgressBar();
        Button buttonPrimary = new Button();
        Hr hr2 = new Hr();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        h4.setText("Adicionar Novo Funcionario");
        h4.setWidth("max-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        textField.setLabel("Text field");
        textField.setWidth("min-content");
        datePicker.setLabel("Date picker");
        datePicker.setWidth("min-content");
        textField2.setLabel("Text field");
        textField2.setWidth("min-content");
        formLayout2Col.setWidth("100%");
        comboBox.setLabel("Combo Box");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        comboBox2.setLabel("Combo Box");
        comboBox2.setWidth("min-content");
        setComboBoxSampleData(comboBox2);
        link.setText("Hello Vaadin");
        link.setHref("#");
        link.setWidth("min-content");
        link2.setText("Hello Vaadin");
        link2.setHref("#");
        link2.setWidth("min-content");
        accordion.setWidth("100%");
        setAccordionSampleData(accordion);
        formLayout2Col2.setWidth("100%");
        textField3.setLabel("Text field");
        textField3.setWidth("min-content");
        textField4.setLabel("Text field");
        textField4.setWidth("min-content");
        h5.setText("Adicionar Novo Telefone");
        h5.setWidth("max-content");
        layoutColumn3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        formLayout2Col3.setWidth("100%");
        comboBox3.setLabel("Combo Box");
        comboBox3.setWidth("min-content");
        setComboBoxSampleData(comboBox3);
        textField5.setLabel("Text field");
        textField5.setWidth("min-content");
        link3.setText("Hello Vaadin");
        link3.setHref("#");
        link3.setWidth("min-content");
        buttonSecondary.setText("+");
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.END, buttonSecondary);
        buttonSecondary.setWidth("min-content");
        minimalistGrid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS);
        minimalistGrid.setWidth("100%");
        minimalistGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(minimalistGrid);
        h52.setText("Adicionar Novo Endereço");
        h52.setWidth("max-content");
        layoutColumn4.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");
        formLayout2Col4.setWidth("100%");
        comboBox4.setLabel("Combo Box");
        comboBox4.setWidth("min-content");
        setComboBoxSampleData(comboBox4);
        comboBox5.setLabel("Combo Box");
        comboBox5.setWidth("min-content");
        setComboBoxSampleData(comboBox5);
        link4.setText("Hello Vaadin");
        link4.setHref("#");
        link4.setWidth("min-content");
        link5.setText("Hello Vaadin");
        link5.setHref("#");
        link5.setWidth("min-content");
        formLayout3Col2.setWidth("100%");
        formLayout3Col2.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        textField6.setLabel("Text field");
        textField6.setWidth("min-content");
        textField7.setLabel("Text field");
        textField7.setWidth("min-content");
        textField8.setLabel("Text field");
        textField8.setWidth("min-content");
        buttonSecondary2.setText("+");
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.END, buttonSecondary2);
        buttonSecondary2.setWidth("min-content");
        minimalistGrid2.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS);
        minimalistGrid2.setWidth("100%");
        minimalistGrid2.getStyle().set("flex-grow", "0");
        setGridSampleData(minimalistGrid2);
        progressBar.setValue(0.5);
        buttonPrimary.setText("Salvar");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutColumn2);
        layoutColumn2.add(h4);
        layoutColumn2.add(hr);
        layoutColumn2.add(formLayout3Col);
        formLayout3Col.add(textField);
        formLayout3Col.add(datePicker);
        formLayout3Col.add(textField2);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(comboBox);
        formLayout2Col.add(comboBox2);
        formLayout2Col.add(link);
        formLayout2Col.add(link2);
        layoutColumn2.add(accordion);
        layoutColumn2.add(formLayout2Col2);
        formLayout2Col2.add(textField3);
        formLayout2Col2.add(textField4);
        layoutColumn2.add(h5);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(formLayout2Col3);
        formLayout2Col3.add(comboBox3);
        formLayout2Col3.add(textField5);
        formLayout2Col3.add(link3);
        layoutColumn3.add(buttonSecondary);
        layoutColumn3.add(minimalistGrid);
        layoutColumn2.add(h52);
        layoutColumn2.add(layoutColumn4);
        layoutColumn4.add(formLayout2Col4);
        formLayout2Col4.add(comboBox4);
        formLayout2Col4.add(comboBox5);
        formLayout2Col4.add(link4);
        formLayout2Col4.add(link5);
        layoutColumn4.add(formLayout3Col2);
        formLayout3Col2.add(textField6);
        formLayout3Col2.add(textField7);
        formLayout3Col2.add(textField8);
        layoutColumn4.add(buttonSecondary2);
        layoutColumn4.add(minimalistGrid2);
        layoutColumn2.add(progressBar);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(hr2);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }

    private void setAccordionSampleData(Accordion accordion) {
        Span name = new Span("Sophia Williams");
        Span email = new Span("sophia.williams@company.com");
        Span phone = new Span("(501) 555-9128");
        VerticalLayout personalInformationLayout = new VerticalLayout(name, email, phone);
        personalInformationLayout.setSpacing(false);
        personalInformationLayout.setPadding(false);
        accordion.add("Personal information", personalInformationLayout);
        Span street = new Span("4027 Amber Lake Canyon");
        Span zipCode = new Span("72333-5884 Cozy Nook");
        Span city = new Span("Arkansas");
        VerticalLayout billingAddressLayout = new VerticalLayout();
        billingAddressLayout.setSpacing(false);
        billingAddressLayout.setPadding(false);
        billingAddressLayout.add(street, zipCode, city);
        accordion.add("Billing address", billingAddressLayout);
        Span cardBrand = new Span("Mastercard");
        Span cardNumber = new Span("1234 5678 9012 3456");
        Span expiryDate = new Span("Expires 06/21");
        VerticalLayout paymentLayout = new VerticalLayout();
        paymentLayout.setSpacing(false);
        paymentLayout.setPadding(false);
        paymentLayout.add(cardBrand, cardNumber, expiryDate);
        accordion.add("Payment", paymentLayout);
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
