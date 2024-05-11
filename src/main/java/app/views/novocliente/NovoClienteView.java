package app.views.novocliente;

import app.controller.ControllerTipoTelefone;
import app.data.SamplePerson;
import app.model.Telefone;
import app.model.TipoTelefone;
import app.services.SamplePersonService;
import app.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.vaadin.flow.data.provider.ListDataProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("NovoCliente")
@Route(value = "my-view2", layout = MainLayout.class)
@Uses(Icon.class)
public class NovoClienteView extends Composite<VerticalLayout> {
    ControllerTipoTelefone controller = new ControllerTipoTelefone();
    List<Telefone> telefones = new ArrayList<>();
    List<ComboBox<TipoTelefone>> comboBoxes = new ArrayList<>();
    List<TextField> textFields = new ArrayList<>();
    VerticalLayout layoutColumn2 = new VerticalLayout();
    H4 h4 = new H4();
    VerticalLayout layoutColumn3 = new VerticalLayout();
    Hr hr = new Hr();
    TextField textField = new TextField();
    Accordion accordion = new Accordion();
    HorizontalLayout layoutRow = new HorizontalLayout();
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
    Button buttonPrimary = new Button();
    Hr hr2 = new Hr();

    public NovoClienteView() {
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
        textField.setPlaceholder("Nome");
        textField.setWidth("min-content");
        accordion.setWidth("100%");
        setAccordionSampleData(accordion);


        
        layoutRow.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, formLayout2Col);
        formLayout2Col.setWidth("100%");   
        
        h5.setText("Adicionar Telefones");
        h5.setWidth("max-content");
        layoutColumn4.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");
        formLayout2Col2.setWidth("100%");
        comboBox.setPlaceholder("Telefone Tipo");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        textField4.setPlaceholder("Número");
        textField4.setWidth("min-content");
        Button buttonInsideLink = new Button("Adicionar Tipo de Telefone");
        buttonInsideLink.addClickListener(event -> openDialog());
        link.add(buttonInsideLink);
        buttonSecondary.setText("+");
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.END, buttonSecondary);
        buttonSecondary.setWidth("min-content");

        buttonSecondary.addClickListener(event -> {  
            TipoTelefone tipoTelefoneSelecionado = (TipoTelefone) comboBox.getValue();
            String numero = textField4.getValue();
        
            boolean camposPreenchidos = tipoTelefoneSelecionado != null && !numero.isEmpty();
            boolean valoresUnicos = isTelefoneUnico(numero);

            for (ComboBox<TipoTelefone> cb : comboBoxes) {
                if (cb.getValue() == null) {
                    camposPreenchidos = false;
                    break;
                }
            }
            for (TextField tf : textFields) {
                if (tf.getValue().isEmpty()) {
                    camposPreenchidos = false;
                    break;
                }
            }
        
            if (camposPreenchidos) {
                if (valoresUnicos) {
                    ComboBox<TipoTelefone> novoComboBox = new ComboBox<>();
                    novoComboBox.setPlaceholder("Telefone Tipo");
                    setComboBoxSampleData(novoComboBox);
        
                    TextField novoTextField = new TextField();
                    novoTextField.setPlaceholder("Número");
        
                    comboBoxes.add(novoComboBox);
                    textFields.add(novoTextField);
                    layoutColumn4.add(novoComboBox);
                    layoutColumn4.add(novoTextField);
        
                    Telefone novoTelefone = new Telefone(Integer.parseInt(numero), tipoTelefoneSelecionado);
                    telefones.add(novoTelefone);
                } else {
                    Notification notification = new Notification(
                        "O número de telefone já está em uso. Por favor, insira um número de telefone único.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            } else {
                Notification notification = new Notification(
                    "Por favor, preencha todos os campos antes de adicionar mais.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
        });
        h52.setText("Adicionar Endereços");
        h52.setWidth("max-content");
        layoutColumn5.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.setWidth("100%");
        layoutColumn5.getStyle().set("flex-grow", "1");
        formLayout2Col3.setWidth("100%");
        comboBox2.setPlaceholder("Endereço Tipo");
        comboBox2.setWidth("min-content");
        setComboBoxSampleData(comboBox2);
        comboBox3.setPlaceholder("Cidade");
        comboBox3.setWidth("min-content");
        setComboBoxSampleData(comboBox3);
        Button buttonInsideLink2 = new Button("Adicionar Tipo de Endereço");
        buttonInsideLink.addClickListener(event -> openDialog());
        link2.add(buttonInsideLink);
        Button buttonInsideLink3 = new Button("Adicionar Tipo de Endereço");
        buttonInsideLink.addClickListener(event -> openDialog());
        link2.add(buttonInsideLink);
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        textField5.setPlaceholder("Logradouro");
        textField5.setWidth("min-content");
        textField6.setPlaceholder("Bairro");
        textField6.setWidth("min-content");
        textField7.setPlaceholder("Número");
        textField7.setWidth("min-content");
        buttonSecondary2.setText("+");
        layoutColumn5.setAlignSelf(FlexComponent.Alignment.END, buttonSecondary2);
        buttonSecondary2.setWidth("min-content");
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
     //   layoutRow.add(radioGroup);
        layoutRow.add(formLayout2Col);
   //     formLayout2Col.add(textField2);
    //    formLayout2Col.add(textField3);
        layoutColumn3.add(h5);
        layoutColumn3.add(layoutColumn4);
        layoutColumn4.add(formLayout2Col2);
        formLayout2Col2.add(comboBox);
        formLayout2Col2.add(textField4);
        formLayout2Col2.add(link);
        layoutColumn4.add(buttonSecondary);
        layoutColumn3.add(buttonSecondary); 
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
        layoutColumn3.add(buttonPrimary);
        layoutColumn3.add(hr2);
    }

    private void setAccordionSampleData(Accordion accordion) {
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>("", "Pessoa Jurídica", "Pessoa Física");
        VerticalLayout informacoes = new VerticalLayout();
        informacoes.setSpacing(false);
        informacoes.setPadding(false);
        
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        textField2.setVisible(false);
        textField3.setVisible(false);
        
        HorizontalLayout textFieldsLayout = new HorizontalLayout();
        textFieldsLayout.add(textField2, textField3);
        
        radioGroup.addValueChangeListener(event -> {
            if ("Pessoa Jurídica".equals(event.getValue())) {
                textField2.setVisible(true);
                textField2.setPlaceholder("Insica CNPJ");
                textField3.setVisible(true);
                textField3.setPlaceholder("Insira Inscrição Estadual");
            } else if ("Pessoa Física".equals(event.getValue())) {
                textField2.setVisible(true);
                textField2.setPlaceholder("Insica CPF");
                textField3.setVisible(true);
                textField3.setPlaceholder("Insira RG");
            } else {
                textField2.setVisible(false);
                textField3.setVisible(false);
            }
        });
        
        informacoes.add(radioGroup, textFieldsLayout);
        accordion.add("Informações Pessoais", informacoes);


        Span street = new Span("4027 Amber Lake Canyon");
        Span zipCode = new Span("72333-5884 Cozy Nook");
        Span city = new Span("Arkansas");
        VerticalLayout telefone = new VerticalLayout();
        telefone.setSpacing(false);
        telefone.setPadding(false);
        telefone.add(street, zipCode, city);
        accordion.add("Telefone", telefone);
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

    private void setComboBoxSampleData(ComboBox<TipoTelefone> comboBox) {
        List<TipoTelefone> tiposTelefone = controller.pesquisarTodos();
        comboBox.setItems(tiposTelefone);
        comboBox.setItemLabelGenerator(tipoTelefone -> tipoTelefone.getNome());
    }

    private boolean isTelefoneUnico(String numero) {
        for (Telefone telefone : telefones) {
            if (telefone.getNumero() == Integer.parseInt(numero)) {
                return false; 
            }
        }
        return true; 
    }

    private void openDialog() {
        Dialog dialog = new Dialog();

        FormLayout formLayout = new FormLayout();
        TextField nomeField = new TextField("Nome");
        formLayout.add(nomeField);

        Button confirmarButton = new Button("Confirmar", event -> {
            TipoTelefone tipoTelefone = new TipoTelefone();
            tipoTelefone.setNome(nomeField.getValue());
            if (controller.inserir(tipoTelefone) == true) {
                Notification notification = new Notification(
                        "Autor salvo com sucesso.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            } else {
                Notification notification = new Notification(
                        "Erro ao salvar. Verifique se todos os dados foram preenchidos.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
            dialog.close();
        });
        Button cancelarButton = new Button("Cancelar", event -> dialog.close());

        formLayout.add(confirmarButton, cancelarButton);
        dialog.add(formLayout);
        dialog.open();
    }

}
