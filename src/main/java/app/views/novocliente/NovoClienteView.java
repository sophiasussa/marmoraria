package app.views.novocliente;

import app.data.SamplePerson;
import app.services.SamplePersonService;
import app.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("NovoCliente")
@Route(value = "my-view2", layout = MainLayout.class)
@Uses(Icon.class)
public class NovoClienteView extends Composite<VerticalLayout> {

    public NovoClienteView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H4 h4 = new H4();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        Hr hr = new Hr();
        TextField textField = new TextField();
        Accordion accordion = new Accordion();
        HorizontalLayout layoutRow = new HorizontalLayout();
        RadioButtonGroup radioGroup = new RadioButtonGroup();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        H5 h5 = new H5();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        FormLayout formLayout2Col2 = new FormLayout();
        ComboBox comboBox = new ComboBox();
        TextField textField4 = new TextField();
        Anchor link = new Anchor();
        Button buttonSecondary = new Button();
        Grid minimalistGrid = new Grid(SamplePerson.class);
        H5 h52 = new H5();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        FormLayout formLayout2Col3 = new FormLayout();
        ComboBox comboBox2 = new ComboBox();
        ComboBox comboBox3 = new ComboBox();
        Anchor link2 = new Anchor();
        Anchor link3 = new Anchor();
        FormLayout formLayout3Col = new FormLayout();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();
        Button buttonSecondary2 = new Button();
        Grid basicGrid = new Grid(SamplePerson.class);
        ProgressBar progressBar = new ProgressBar();
        Button buttonPrimary = new Button();
        Hr hr2 = new Hr();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        h4.setText("Adicionar Novo Cliente");
        h4.setWidth("max-content");
        layoutColumn3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        textField.setLabel("Text field");
        textField.setWidth("min-content");
        accordion.setWidth("100%");
        setAccordionSampleData(accordion);
        layoutRow.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        radioGroup.setLabel("Radio Group");
        radioGroup.setWidth("min-content");
        radioGroup.setItems("Order ID", "Product Name", "Customer", "Status");
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, formLayout2Col);
        formLayout2Col.setWidth("100%");
        textField2.setLabel("Text field");
        textField2.setWidth("min-content");
        textField3.setLabel("Text field");
        textField3.setWidth("min-content");
        h5.setText("Adicionar Telefones");
        h5.setWidth("max-content");
        layoutColumn4.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");
        formLayout2Col2.setWidth("100%");
        comboBox.setLabel("Combo Box");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        textField4.setLabel("Text field");
        textField4.setWidth("min-content");
        link.setText("Hello Vaadin");
        link.setHref("#");
        link.setWidth("min-content");
        buttonSecondary.setText("+");
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.END, buttonSecondary);
        buttonSecondary.setWidth("min-content");
        minimalistGrid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS);
        minimalistGrid.setWidth("100%");
        minimalistGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(minimalistGrid);
        h52.setText("Adicionar Endereços");
        h52.setWidth("max-content");
        layoutColumn5.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.setWidth("100%");
        layoutColumn5.getStyle().set("flex-grow", "1");
        formLayout2Col3.setWidth("100%");
        comboBox2.setLabel("Combo Box");
        comboBox2.setWidth("min-content");
        setComboBoxSampleData(comboBox2);
        comboBox3.setLabel("Combo Box");
        comboBox3.setWidth("min-content");
        setComboBoxSampleData(comboBox3);
        link2.setText("Hello Vaadin");
        link2.setHref("#");
        link2.setWidth("min-content");
        link3.setText("Hello Vaadin");
        link3.setHref("#");
        link3.setWidth("min-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        textField5.setLabel("Text field");
        textField5.setWidth("min-content");
        textField6.setLabel("Text field");
        textField6.setWidth("min-content");
        textField7.setLabel("Text field");
        textField7.setWidth("min-content");
        buttonSecondary2.setText("+");
        layoutColumn5.setAlignSelf(FlexComponent.Alignment.END, buttonSecondary2);
        buttonSecondary2.setWidth("min-content");
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(basicGrid);
        progressBar.setValue(0.5);
        buttonPrimary.setText("Salvar");
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutColumn2);
        layoutColumn2.add(h4);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(hr);
        layoutColumn3.add(textField);
        layoutColumn3.add(accordion);
        layoutColumn3.add(layoutRow);
        layoutRow.add(radioGroup);
        layoutRow.add(formLayout2Col);
        formLayout2Col.add(textField2);
        formLayout2Col.add(textField3);
        layoutColumn3.add(h5);
        layoutColumn3.add(layoutColumn4);
        layoutColumn4.add(formLayout2Col2);
        formLayout2Col2.add(comboBox);
        formLayout2Col2.add(textField4);
        formLayout2Col2.add(link);
        layoutColumn4.add(buttonSecondary);
        layoutColumn4.add(minimalistGrid);
        layoutColumn3.add(h52);
        layoutColumn3.add(layoutColumn5);
        layoutColumn5.add(formLayout2Col3);
        formLayout2Col3.add(comboBox2);
        formLayout2Col3.add(comboBox3);
        formLayout2Col3.add(link2);
        formLayout2Col3.add(link3);
        layoutColumn5.add(formLayout3Col);
        formLayout3Col.add(textField5);
        formLayout3Col.add(textField6);
        formLayout3Col.add(textField7);
        layoutColumn5.add(buttonSecondary2);
        layoutColumn5.add(basicGrid);
        layoutColumn3.add(progressBar);
        layoutColumn3.add(buttonPrimary);
        layoutColumn3.add(hr2);
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

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}