package app.views.cliente;

import app.components.avataritem.AvatarItem;
import app.controller.ControllerCliente;
import app.data.SamplePerson;
import app.model.Cliente;
import app.model.Telefone;
import app.services.SamplePersonService;
import app.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Cliente")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class ClienteView extends Composite<VerticalLayout> {

    public ClienteView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        ControllerCliente controller = new ControllerCliente(); 
        Tabs tabs = new Tabs();
        AvatarItem avatarItem = new AvatarItem();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        TextField textField = new TextField();
        Button buttonPrimary = new Button();
        Grid<Cliente> minimalistGrid = new Grid(Cliente.class, false);
        List<Cliente> clientes = controller.listarTodos(); 
        minimalistGrid.setItems(clientes);

        minimalistGrid.addColumn(Cliente::getNome).setHeader("Nome");
        minimalistGrid.addColumn(Cliente::getCpf).setHeader("CPF");
        minimalistGrid.addColumn(Cliente::getRg).setHeader("RG");

        minimalistGrid.addComponentColumn(cliente -> {
            Button telefoneButton = new Button(VaadinIcon.PHONE.create());
            telefoneButton.getStyle().set("border-radius", "50%");
            telefoneButton.addClickListener(event -> {
                Dialog dialog = new Dialog();
                dialog.add("Telefones do Cliente:");
                cliente.getTelefones().forEach(telefone -> {
                    dialog.add(telefone.getNumero() + " (" + telefone.getTipoTelefone().getNome() + ")");
                });
                dialog.open();
            });
            return telefoneButton;
        }).setHeader("Telefones");

        minimalistGrid.addComponentColumn(cliente -> {
            Button enderecosButton = new Button(VaadinIcon.HOME.create());
            enderecosButton.getStyle().set("border-radius", "50%");
            enderecosButton.addClickListener(event -> {
                Dialog dialog = new Dialog();
                dialog.add("Endereços do Cliente:");
                cliente.getEnderecos().forEach(endereco -> {
                    dialog.add(endereco.getLogradouro() + ", " + endereco.getNumero() + " - " + endereco.getBairro() + ", " + endereco.getCidade().getNome() + " (" + endereco.getTipoEndereco().getNome() + ")");
                });
                dialog.open();
            });
            return enderecosButton;
        }).setHeader("Endereços");
        
        minimalistGrid.addComponentColumn(cliente -> {
            MenuBar menuBar = new MenuBar();
            MenuItem menuItem = menuBar.addItem("...");
            SubMenu subMenu = menuItem.getSubMenu();
            subMenu.addItem("Editar");
            subMenu.addItem("Excluir");
            return menuBar;
        }).setHeader("Opções");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        tabs.setWidth("100%");
        setTabsSampleData(tabs);
        avatarItem.setWidth("min-content");
        setAvatarItemSampleData(avatarItem);
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutRow2.setAlignItems(Alignment.END);
        layoutRow2.setJustifyContentMode(JustifyContentMode.END);
        textField.setLabel("Text field");
        layoutRow2.setAlignSelf(FlexComponent.Alignment.CENTER, textField);
        textField.setWidth("min-content");
        buttonPrimary.setText("Button");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        minimalistGrid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
            GridVariant.LUMO_NO_ROW_BORDERS);
        minimalistGrid.setWidth("100%");
        minimalistGrid.getStyle().set("flex-grow", "0");
        getContent().add(layoutRow);
        layoutRow.add(tabs);
        layoutRow.add(avatarItem);
        getContent().add(layoutColumn2);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(textField);
        layoutRow2.add(buttonPrimary);
        layoutColumn2.add(minimalistGrid);
    }

    private void setTabsSampleData(Tabs tabs) {
        tabs.add(new Tab("Dashboard"));
        tabs.add(new Tab("Payment"));
        tabs.add(new Tab("Shipping"));
    }

    private void setAvatarItemSampleData(AvatarItem avatarItem) {
        avatarItem.setHeading("Aria Bailey");
        avatarItem.setDescription("Endocrinologist");
        avatarItem.setAvatar(new Avatar("Aria Bailey"));
    }
}
