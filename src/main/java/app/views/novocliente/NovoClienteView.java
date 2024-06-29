package app.views.novocliente;

import app.controller.ControllerCidade;
import app.controller.ControllerCliente;
import app.controller.ControllerTipoEndereco;
import app.controller.ControllerTipoTelefone;
import app.data.SamplePerson;
import app.model.Cidade;
import app.model.Cliente;
import app.model.Endereco;
import app.model.Pessoa;
import app.model.Telefone;
import app.model.TipoEndereco;
import app.model.TipoTelefone;
import app.services.SamplePersonService;
import app.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
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
    ControllerTipoEndereco controller1 = new ControllerTipoEndereco();
    ControllerCidade controller2 = new ControllerCidade();
    ControllerCliente controller3 = new ControllerCliente();
    Grid<Telefone> grid1 = new Grid<>();
    Grid<Endereco> grid2 = new Grid<>();
    List<Telefone> telefones = new ArrayList<>();
    List<Endereco> enderecos = new ArrayList<>();
    VerticalLayout layoutColumn2 = new VerticalLayout();
    VerticalLayout layoutColumn3 = new VerticalLayout();
    TextField textField = new TextField();
    Accordion accordion = new Accordion();
    HorizontalLayout layoutRow = new HorizontalLayout();
    FormLayout formLayout2Col = new FormLayout();
    Anchor link = new Anchor();
    Button buttonPrimary = new Button();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();
    H5 h52 = new H5();

    public NovoClienteView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidthFull();
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");


        textField.setPlaceholder("Nome");
        textField.setWidth("min-content");
        textField.setWidth("300px");
        accordion.setWidth("100%");
        Span asterisco = new Span("*");
        asterisco.getStyle()
                .set("color", "red")
                .set("position", "relative")
                .set("left", "-230px");
        asterisco.getElement().setAttribute("title", "Informação obrigatória");
        asterisco.getStyle().set("cursor", "pointer");

        textField.addFocusListener(event -> {
            asterisco.setVisible(false);
        });

        textField.addBlurListener(event -> {
            if (textField.isEmpty()) {
                asterisco.setVisible(true);
            }
        });

        textField.setSuffixComponent(asterisco);
        setAccordionSampleData(accordion);
        layoutRow.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, formLayout2Col);
        buttonPrimary.setText("Salvar");
        buttonPrimary.getStyle().set("background-color", "#228B22");
        buttonPrimary.getStyle().set("border-radius", "10px");
        buttonPrimary.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)");
        buttonPrimary.getStyle().set("cursor", "pointer");

        layoutColumn3.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(event -> {
            Cliente cliente = new Cliente();
            
            cliente.setNome(textField.getValue());
            cliente.setCpf(Long.parseLong(textField2.getValue()));
            cliente.setRg(Long.parseLong(textField3.getValue()));
            
            cliente.setEnderecos(enderecos);
            
            cliente.setTelefones(telefones);
            
            if (controller3.inserir(cliente)) {
                Notification.show("Cliente inserido com sucesso!", 3000, Notification.Position.MIDDLE);
            } else {
                Notification.show("Erro ao inserir cliente. Verifique os dados e tente novamente.", 3000, Notification.Position.MIDDLE);
            }
        });
        getContent().add(layoutColumn2);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(textField);
        layoutColumn3.add(accordion);
        layoutRow.add(formLayout2Col);
        layoutColumn3.getStyle().set("border", "0.1px solid #CED4DA");
        layoutColumn3.getStyle().set("box-shadow", "0 0 6px rgba(0, 0, 0, 0.2)");
        layoutColumn2.add(buttonPrimary);
    }

    private void setAccordionSampleData(Accordion accordion) {
        HorizontalLayout customHeader = new HorizontalLayout();
        customHeader.setSpacing(false);
        customHeader.setAlignItems(FlexComponent.Alignment.CENTER);

        Span headerText = new Span("Informações Pessoais");
        Span asterisk = new Span("*");
        asterisk.getStyle().set("color", "red").set("margin-left", "4px");
        asterisk.getElement().setAttribute("title", "Informação obrigatória");
        customHeader.add(headerText, asterisk);
        
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>("", "Pessoa Jurídica", "Pessoa Física");
        VerticalLayout informacoes = new VerticalLayout();
        informacoes.setSpacing(false);
        informacoes.setPadding(false);

        textField2.setVisible(false);
        textField3.setVisible(false);
        textField2.setWidth("300px"); 
        textField3.setWidth("300px");

        textField2.getStyle().set("margin-top", "20px");
        textField3.getStyle().set("margin-top", "20px");

        HorizontalLayout textFieldsLayout = new HorizontalLayout();
        textFieldsLayout.add(textField2, textField3);
        radioGroup.addValueChangeListener(event -> {
            if ("Pessoa Jurídica".equals(event.getValue())) {
                textField2.setVisible(true);
                textField2.setPlaceholder("Insira CNPJ");
                textField3.setVisible(true);
                textField3.setPlaceholder("Insira Inscrição Estadual");
            } else if ("Pessoa Física".equals(event.getValue())) {
                textField2.setVisible(true);
                textField2.setPlaceholder("Insira CPF");
                textField3.setVisible(true);
                textField3.setPlaceholder("Insira RG");
            } else {
                textField2.setVisible(false);
                textField3.setVisible(false);
            }
        });
        
        informacoes.add(radioGroup, textFieldsLayout);
        AccordionPanel accordionPanel = new AccordionPanel();
        accordionPanel.setSummary(customHeader);
        accordionPanel.add(informacoes);
        accordion.add(accordionPanel);


        H5 h5 = new H5();
        h5.setText("Adicionar Telefones");
        h5.setWidth("max-content");
        h5.getStyle().set("margin-top", "1em");
        h5.getStyle().set("margin-bottom", "1em");
        FormLayout formLayout2Col2 = new FormLayout();
        FormLayout formLayout3Col2 = new FormLayout();
        ComboBox<TipoTelefone> comboBox = new ComboBox<>();
        comboBox.setPlaceholder("Telefone Tipo");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        TextField textField4 = new TextField();
        textField4.setPlaceholder("Número");
        textField4.setWidth("min-content");
        Button buttonInsideLink = new Button("Adicionar Tipo de Telefone");
        buttonInsideLink.addClickListener(event -> openDialog());
        buttonInsideLink.getStyle().set("box-shadow", "0 0 4px rgba(0, 0, 0, 0.2)");
        link.add(buttonInsideLink);
        grid1.addColumn(Telefone::getNumero).setHeader("Número");
        grid1.addColumn(telefone -> telefone.getTipoTelefone().getNome()).setHeader("Tipo de Telefone");
        ListDataProvider<Telefone> dataProvider;
        dataProvider = new ListDataProvider<>(telefones);
        grid1.setDataProvider(dataProvider);
        grid1.setVisible(false);   
        grid1.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        grid1.addComponentColumn(telefone -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteButton.addClickListener(event -> {
                telefones.remove(telefone);
                dataProvider.refreshAll();
                updateGridHeight();
        
                Notification notification = new Notification(
                    "Telefone removido com sucesso.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            });
            return deleteButton;
        }).setHeader("Ações");
        
        Button buttonSecondary = new Button();
        buttonSecondary.setText("+");
        buttonSecondary.setWidth("min-content");
        buttonSecondary.getStyle().set("margin-left", "auto");
        buttonSecondary.getStyle().set("background-color", "#1A73E8");
        buttonSecondary.getStyle().set("color", "#FFFFFF");
        buttonSecondary.getStyle().set("border-radius", "10px");
        buttonSecondary.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)");
        buttonSecondary.getStyle().set("cursor", "pointer");
        buttonSecondary.addClickListener(event -> {  
            TipoTelefone tipoTelefoneSelecionado = comboBox.getValue();
            String numeroStr = textField4.getValue();

            if (telefones.size() < 5){
                try {
                    long numero = Long.parseLong(numeroStr);
        
                    boolean camposPreenchidos = tipoTelefoneSelecionado != null && !numeroStr.isEmpty();
                    boolean valoresUnicos = isTelefoneUnico(numero);
    
                    if (camposPreenchidos) {
                        if (valoresUnicos) {
                            Telefone novoTelefone = new Telefone();
                            novoTelefone.setTipoTelefone(tipoTelefoneSelecionado);
                            novoTelefone.setNumero(numero);
        
                            telefones.add(novoTelefone);
                            dataProvider.refreshAll();
    
                            comboBox.clear();
                            textField4.clear();
        
                            Notification notification = new Notification(
                                "Telefone adicionado com sucesso.", 3000);
                            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                            notification.setPosition(Notification.Position.MIDDLE);
                            notification.open();
    
                            grid1.setVisible(true);
                            updateGridHeight();
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
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido: " + numeroStr);
                    Notification notification = new Notification(
                        "O número de telefone deve ser um valor numérico válido.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            } else {
                Notification notification = new Notification(
                    "Não é possível adicionar mais de 5 números de telefone.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
        });

        formLayout2Col2.add(comboBox, textField4, link);
        VerticalLayout telefone = new VerticalLayout();
        telefone.setSpacing(false);
        telefone.setPadding(false);
        telefone.add(h5, formLayout2Col2, formLayout3Col2, buttonSecondary, grid1); 
        accordion.add("Telefone", telefone);

        h52.setText("Adicionar Endereços");
        h52.setWidth("max-content");
        FormLayout formLayout2Col3 = new FormLayout();
        Anchor link2 = new Anchor();
        Anchor link3 = new Anchor();
        FormLayout formLayout3Col = new FormLayout();
        formLayout3Col.setResponsiveSteps(
            new ResponsiveStep("0", 1),      
            new ResponsiveStep("500px", 3)  
        );
        ComboBox<TipoEndereco> comboBox2 = new ComboBox();
        ComboBox<Cidade> comboBox3 = new ComboBox();
        comboBox2.setPlaceholder("Endereço Tipo");
        comboBox2.setWidth("min-content");
        setComboBoxSampleData1(comboBox2);
        comboBox3.setPlaceholder("Cidade");
        comboBox3.setWidth("min-content");
        setComboBoxSampleData2(comboBox3);
        Button buttonInsideLink2 = new Button("Adicionar Tipo de Endereço");
        buttonInsideLink2.addClickListener(event -> openDialog2());
        buttonInsideLink2.getStyle().set("box-shadow", "0 0 4px rgba(0, 0, 0, 0.2)");
        link2.add(buttonInsideLink2);
        Button buttonInsideLink3 = new Button("Adicionar Cidade");
        buttonInsideLink3.addClickListener(event -> openDialog3());
        buttonInsideLink3.getStyle().set("box-shadow", "0 0 4px rgba(0, 0, 0, 0.2)");
        link3.add(buttonInsideLink3);
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();
        textField5.setPlaceholder("Logradouro");
        textField5.setWidth("min-content");
        textField6.setPlaceholder("Bairro");
        textField6.setWidth("min-content");
        textField7.setPlaceholder("Número");
        textField7.setWidth("min-content");
        VerticalLayout titulo = new VerticalLayout();
        titulo.add(h52);
        formLayout2Col3.add(comboBox2);
        formLayout2Col3.add(comboBox3);
        formLayout2Col3.add(link2);
        formLayout2Col3.add(link3);

        grid2 = new Grid<>(Endereco.class, false);
        grid2.addColumn(endereco -> endereco.getCidade().getNome()).setHeader("Cidade");
        grid2.addColumn(endereco -> endereco.getTipoEndereco().getNome()).setHeader("Tipo de Endereço");
        grid2.addColumn(Endereco::getBairro).setHeader("Bairro");
        grid2.addColumn(Endereco::getNumero).setHeader("Número");
        grid2.addColumn(Endereco::getLogradouro).setHeader("Logradouro");

        ListDataProvider<Endereco> dataaProvider = new ListDataProvider<>(enderecos);
        grid2.setDataProvider(dataaProvider);
        grid2.setVisible(false);   
        grid2.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        grid2.addComponentColumn(endereco -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteButton.addClickListener(event -> {
                enderecos.remove(endereco);
                dataaProvider.refreshAll();

                Notification notification = new Notification(
                    "Endereco removido com sucesso.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            });
            return deleteButton;
        }).setHeader("Ações");

        Button buttonSecondary2 = new Button();
        buttonSecondary2.setText("+");
        buttonSecondary2.setWidth("min-content");
        buttonSecondary2.getStyle().set("margin-left", "auto");
        buttonSecondary2.getStyle().set("background-color", "#1A73E8");
        buttonSecondary2.getStyle().set("color", "#FFFFFF");
        buttonSecondary2.getStyle().set("border-radius", "10px");
        buttonSecondary2.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)");
        buttonSecondary2.getStyle().set("cursor", "pointer");
        buttonSecondary2.addClickListener(event -> {
            TipoEndereco tipoEnderecoSelecionado = comboBox2.getValue();
            Cidade cidadeSelecionada = comboBox3.getValue();
            String logradouro = textField5.getValue();
            String bairro = textField6.getValue();
            int numero = Integer.parseInt(textField7.getValue());

            if(enderecos.size() < 5){
                try{
                    boolean camposPreenchidos = tipoEnderecoSelecionado != null 
                    && cidadeSelecionada != null 
                    && !logradouro.isEmpty() 
                    && !bairro.isEmpty() 
                    && !textField7.isEmpty();

                    if(camposPreenchidos){
                        Endereco endereco = new Endereco();
                        endereco.setTipoEndereco(tipoEnderecoSelecionado);
                        endereco.setCidade(cidadeSelecionada);
                        endereco.setBairro(bairro);
                        endereco.setLogradouro(logradouro);
                        endereco.setNumero(numero);

                        enderecos.add(endereco);
                        dataaProvider.refreshAll();
                        comboBox2.clear();
                        comboBox3.clear();
                        textField5.clear();
                        textField6.clear();
                        textField7.clear();

                        Notification notification = new Notification(
                            "Endereco adicionado com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();

                        updateGridHeight2();
                        grid2.setVisible(true);
                    } else {
                        Notification notification = new Notification(
                            "Por favor, preencha todos os campos antes de adicionar mais.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } catch (Exception e){
                    Notification notification = new Notification(
                        "Erro", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            } else {
                Notification notification = new Notification(
                    "Não é possível adicionar mais de 5 números de telefone.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
        });

        formLayout3Col.getStyle().set("margin-top", "30px");
        formLayout3Col.add(textField5, textField6, textField7);

        VerticalLayout enderecos = new VerticalLayout();
        enderecos.setSpacing(false);
        enderecos.setPadding(false);
        enderecos.add(titulo, formLayout2Col3, formLayout3Col, buttonSecondary2, grid2);
        accordion.add("Endereço", enderecos);
    }

    private void updateGridHeight() {
        int rows = telefones.size();
        int rowHeight = 50; 
        int headerHeight = 56; 
    
        grid1.setHeight((rows * rowHeight + headerHeight) + "px");
    }

    private void updateGridHeight2() {
        int rows = enderecos.size();
        int rowHeight = 50; 
        int headerHeight = 56; 
    
        grid2.setHeight((rows * rowHeight + headerHeight) + "px");
    }

    private void setComboBoxSampleData(ComboBox<TipoTelefone> comboBox) {
        List<TipoTelefone> tiposTelefone = controller.pesquisarTodos();
        comboBox.setItems(tiposTelefone);
        comboBox.setItemLabelGenerator(tipoTelefone -> tipoTelefone.getNome());
    }
    
    private void setComboBoxSampleData1(ComboBox<TipoEndereco> comboBox2) {
        List<TipoEndereco> tiposEndereco = controller1.pesquisarTodos();
        comboBox2.setItems(tiposEndereco);
        comboBox2.setItemLabelGenerator(tipoEndereco -> tipoEndereco.getNome());
    }

    private void setComboBoxSampleData2(ComboBox<Cidade> comboBox3) {
        List<Cidade> cidades = controller2.pesquisarTodos();
        comboBox3.setItems(cidades);
        comboBox3.setItemLabelGenerator(cidade -> cidade.getNome());
    }

    private boolean isTelefoneUnico(long numero) {
        return telefones.stream().noneMatch(telefone -> telefone.getNumero() == numero);
    }

    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.setWidth("800px"); 
        dialog.setHeight("600px");

        FormLayout formLayout = new FormLayout();
        TextField nomeField = new TextField("Nome");

        Grid<TipoTelefone> grid = new Grid<>(TipoTelefone.class);
        grid.setColumns("nome");

        List<TipoTelefone> tiposDeTelefone = controller.pesquisarTodos();
        grid.setItems(tiposDeTelefone);

        Editor<TipoTelefone> editor = grid.getEditor();
        Binder<TipoTelefone> binder = new Binder<>(TipoTelefone.class);
        editor.setBinder(binder);

        TextField nomeEditor = new TextField();
        binder.forField(nomeEditor).bind(TipoTelefone::getNome, TipoTelefone::setNome);
        grid.getColumnByKey("nome").setEditorComponent(nomeEditor);

        grid.addItemDoubleClickListener(event -> editor.editItem(event.getItem()));
        editor.addCloseListener(event -> grid.getDataProvider().refreshItem(event.getItem()));
        
        grid.addComponentColumn(tipoTelefone -> {
            Button alterarButton = new Button("Alterar", new Icon(VaadinIcon.COG));
            alterarButton.addClickListener(e -> {
                if (editor.isOpen()) {
                    editor.save();
                    TipoTelefone editedTipoTelefone = editor.getItem();
                    if (controller.alterar(editedTipoTelefone)) {
                        Notification notification = new Notification(
                                "Tipo de Telefone atualizado com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                        
                        tiposDeTelefone.clear();
                        tiposDeTelefone.addAll(controller.pesquisarTodos());
                        grid.getDataProvider().refreshAll();
                    } else {
                        Notification notification = new Notification(
                                "Erro ao atualizar. Verifique se todos os dados foram preenchidos.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } else {
                    editor.editItem(tipoTelefone);
                    nomeEditor.focus();
                }
            });
            return alterarButton;
        }).setHeader("Alterar");
        
        editor.addSaveListener(event -> {
            grid.getDataProvider().refreshItem(event.getItem());
        });
        

        grid.addComponentColumn(tipoTelefone -> {
            Button deletarButton = new Button(new Icon(VaadinIcon.TRASH));
            deletarButton.addClickListener(e -> {
                if (controller.excluir(tipoTelefone)) {
                    Notification notification = new Notification(
                            "Tipo de Telefone deletado com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                    
                    tiposDeTelefone.clear();
                    tiposDeTelefone.addAll(controller.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao deletar. Tente novamente.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            });
            return deletarButton;
        }).setHeader("Deletar");
    

        Button confirmarButton = new Button("Salvar", event -> {
            if(nomeField.isEmpty()){
                Notification notification = new Notification(
                    "Erro: O nome não pode estar vazio.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            } else {
                TipoTelefone tipoTelefone = new TipoTelefone();
                tipoTelefone.setNome(nomeField.getValue());
                if (controller.inserir(tipoTelefone) == true) {
                    Notification notification = new Notification(
                            "Tipo de Telefone salvo com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    tiposDeTelefone.clear();
                    tiposDeTelefone.addAll(controller.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao salvar. Verifique se todos os dados foram preenchidos.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            }
        });
        Button cancelarButton = new Button("Fechar", event -> dialog.close());

        cancelarButton.getStyle()
            .set("background-color", "#FF0000")  
            .set("color", "#FFFFFF")  
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        confirmarButton.getStyle()
            .set("background-color", "#228B22")
            .set("color", "#FFFFFF")
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        HorizontalLayout buttonLayout = new HorizontalLayout(cancelarButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setPadding(false);
        buttonLayout.setSpacing(true);

        formLayout.add(nomeField, confirmarButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, grid, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void openDialog2() {
        Dialog dialog = new Dialog();
        dialog.setWidth("800px");
        dialog.setHeight("600px");

        FormLayout formLayout = new FormLayout();
        TextField nomeField = new TextField("Nome");

        Grid<TipoEndereco> grid = new Grid<>(TipoEndereco.class);
        grid.setColumns("nome");

        List<TipoEndereco> tipoEnderecos = controller1.pesquisarTodos();
        grid.setItems(tipoEnderecos);

        ListDataProvider<TipoEndereco> dataProvider = new ListDataProvider<>(tipoEnderecos);
        grid.setDataProvider(dataProvider);

        Editor<TipoEndereco> editor = grid.getEditor();
        Binder<TipoEndereco> binder = new Binder<>(TipoEndereco.class);
        editor.setBinder(binder);

        TextField nomeEditor = new TextField();
        binder.forField(nomeEditor).bind(TipoEndereco::getNome, TipoEndereco::setNome);
        grid.getColumnByKey("nome").setEditorComponent(nomeEditor);

        grid.addItemDoubleClickListener(event -> editor.editItem(event.getItem()));
        editor.addCloseListener(event -> grid.getDataProvider().refreshItem(event.getItem()));

        grid.addComponentColumn(tipoEndereco -> {
            Button alterarButton = new Button("Alterar", new Icon(VaadinIcon.COG));
            alterarButton.addClickListener(e -> {
                if(editor.isOpen()){
                    editor.save();
                    TipoEndereco editedTipoEndereco = editor.getItem();
                    if(controller1.alterar(editedTipoEndereco)){
                        Notification notification = new Notification(
                            "Tipo de Telefone atualizado com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();

                        tipoEnderecos.clear();
                        tipoEnderecos.addAll(controller1.pesquisarTodos());
                        dataProvider.refreshAll(); 
                    } else {
                        Notification notification = new Notification(
                            "Erro ao atualizar. Verifique se todos os dados foram preenchidos.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                     }
               } else {
                    editor.editItem(tipoEndereco);
                    nomeEditor.focus();
               }
            });
            return alterarButton;
        }).setHeader("Alterar");

        editor.addSaveListener(event -> {
            grid.getDataProvider().refreshItem(event.getItem());
        });

        grid.addComponentColumn(tipoEndereco -> {
            Button deletarButton = new Button(new Icon(VaadinIcon.TRASH));
            deletarButton.addClickListener(e -> {
                if (controller1.excluir(tipoEndereco)) {
                    Notification notification = new Notification(
                            "Tipo de Endereco deletado com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                    
                    tipoEnderecos.clear();
                    tipoEnderecos.addAll(controller1.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao deletar. Tente novamente.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            });
            return deletarButton;
        }).setHeader("Deletar");
    
        Button confirmarButton = new Button("Salvar", event -> {
            if(nomeField.isEmpty()){
                Notification notification = new Notification(
                    "Erro: O nome não pode estar vazio.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            } else {
                TipoEndereco tipoEndereco = new TipoEndereco();
                tipoEndereco.setNome(nomeField.getValue());
                if(controller1.inserir(tipoEndereco) == true){
                    Notification notification = new Notification(
                        "Tipo de Telefone salvo com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
    
                    tipoEnderecos.clear();
                    tipoEnderecos.addAll(controller1.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                        "Erro ao salvar. Verifique se todos os dados foram preenchidos.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            }
        });
        Button cancelarButton = new Button("Fechar", event -> dialog.close());

        cancelarButton.getStyle()
            .set("background-color", "#FF0000")  
            .set("color", "#FFFFFF")  
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        confirmarButton.getStyle()
            .set("background-color", "#228B22")
            .set("color", "#FFFFFF")
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");


        HorizontalLayout buttonLayout = new HorizontalLayout(cancelarButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setPadding(false);
        buttonLayout.setSpacing(true);
        
        formLayout.add(nomeField, confirmarButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, grid, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void openDialog3() {
        Dialog dialog = new Dialog();
        dialog.setWidth("800px");
        dialog.setHeight("600px");

        FormLayout formLayout = new FormLayout();
        TextField nomeField = new TextField("Nome");

        Grid<Cidade> grid = new Grid<>(Cidade.class);
        grid.setColumns("nome");

        List<Cidade> cidades = controller2.pesquisarTodos();
        grid.setItems(cidades);

        ListDataProvider<Cidade> dataProvider = new ListDataProvider<>(cidades);
        grid.setDataProvider(dataProvider);

        Editor<Cidade> editor = grid.getEditor();
        Binder<Cidade> binder = new Binder<>(Cidade.class);
        editor.setBinder(binder);

        TextField nomeEditor = new TextField();
        binder.forField(nomeEditor).bind(Cidade::getNome, Cidade::setNome);
        grid.getColumnByKey("nome").setEditorComponent(nomeEditor);

        grid.addItemClickListener(event -> editor.editItem(event.getItem()));
        editor.addCloseListener(event -> grid.getDataProvider().refreshItem(event.getItem()));

        grid.addComponentColumn(cidade -> {
            Button alterarButton = new Button("Alterar", new Icon(VaadinIcon.COG));
            alterarButton.addClickListener(e -> {
                if(editor.isOpen()){
                    editor.save();
                    Cidade editCidade = editor.getItem();
                    if(controller2.alterar(editCidade)){
                        Notification notification = new Notification(
                            "Cidade atualizada com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();

                        cidades.clear();
                        cidades.addAll(controller2.pesquisarTodos());
                        dataProvider.refreshAll();
                    } else {
                        Notification notification = new Notification(
                            "Erro ao atualizar. Verifique se todos os dados foram preenchidos.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } else {
                    editor.editItem(cidade);
                    nomeEditor.focus();
                }
            });
            return alterarButton;
        }).setHeader("Alterar");

        editor.addSaveListener(event -> {
            grid.getDataProvider().refreshItem(event.getItem());
        });

        grid.addComponentColumn(cidade -> {
            Button deletarButton = new Button(new Icon(VaadinIcon.TRASH));
            deletarButton.addClickListener(e -> {
                if(controller2.excluir(cidade)) {
                    Notification notification = new Notification(
                        "Cidade deletada com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    cidades.clear();
                    cidades.addAll(controller2.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else{
                    Notification notification = new Notification(
                        "Erro ao deletar. Tente novamente.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            });
            return deletarButton;
        }).setHeader("Deletar");

        Button confirmarButton = new Button("Salvar", event -> {
            if(nomeField.isEmpty()){
                Notification notification = new Notification(
                    "Erro: O nome não pode estar vazio.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            } else {
                Cidade cidade = new Cidade();
                cidade.setNome(nomeField.getValue());
                if(controller2.inserir(cidade)){
                    Notification notification = new Notification(
                        "Cidade salva com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
    
                    cidades.clear();
                    cidades.addAll(controller2.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                        "Erro ao salvar. Verifique se todos os dados foram preenchidos.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            }
        });
        Button cancelarButton = new Button("Fechar", event -> dialog.close());

        cancelarButton.getStyle()
            .set("background-color", "#FF0000")  
            .set("color", "#FFFFFF")  
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        confirmarButton.getStyle()
            .set("background-color", "#228B22")
            .set("color", "#FFFFFF")
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");


        HorizontalLayout buttonLayout = new HorizontalLayout(cancelarButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setPadding(false);
        buttonLayout.setSpacing(true);

        formLayout.add(nomeField, confirmarButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, grid, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

}
