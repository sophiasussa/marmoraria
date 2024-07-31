package app.views;

import app.views.addtarefasgerais.AddTarefasGeraisView;
import app.views.cliente.ClienteView;
import app.views.criarperfil.CriarPerfilView;
import app.views.fornecedor.FornecedorView;
import app.views.funcionarios.FuncionariosView;
import app.views.novocliente.NovoClienteView;
import app.views.novofornecedor.NovoFornecedorView;
import app.views.novofuncionario.NovoFuncionarioView;
import app.views.novousuario.NovoUsuarioView;
import app.views.perfilusuario.PerfilUsuarioView;
import app.views.tarefasgerais.TarefasGeraisView;
import app.views.tarefasgeraisconcluido.TarefasGeraisConcluidoView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;
import com.vaadin.flow.component.icon.Icon;



/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Marmosoft");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        SideNav registro = createNavigation();

        SideNav nav = createNavegation2();

        addToDrawer(header, registro, nav, createFooter());
    }

    private SideNav createNavigation() {
        SideNav registro = new SideNav();

        registro.setLabel("Registros");

        registro.getStyle().set("font-weight", "bold");
 //       registro.getStyle().set("color", "var(--lumo-primary-text-color)");
        registro.getStyle().set("background-color", "var(--lumo-primary-color-10pct)");
        registro.getStyle().set("padding", "var(--lumo-space-s)");
        registro.getStyle().set("border-radius", "var(--lumo-border-radius)");
        registro.getStyle().set("display", "block");
        registro.getStyle().set("margin", "var(--lumo-space-xs) 0");

        registro.setCollapsible(true);
        registro.setExpanded(false);
        registro.addItem(new SideNavItem("Cliente", ClienteView.class, VaadinIcon.GROUP.create()));
        registro.addItem(new SideNavItem("Cliente2", NovoClienteView.class, VaadinIcon.GROUP.create()));
        registro.addItem(new SideNavItem("Fornecedor", FornecedorView.class, LineAwesomeIcon.ADDRESS_BOOK.create()));
        registro.addItem(new SideNavItem("Novo Fornecedor", NovoFornecedorView.class, LineAwesomeIcon.ADDRESS_BOOK.create()));
        registro.addItem(new SideNavItem("Funcionários", FuncionariosView.class, VaadinIcon.KEY.create()));
        registro.addItem(new SideNavItem("Novo Funcionário", NovoFuncionarioView.class, VaadinIcon.KEY.create()));

        registro.getElement().appendChild(new Element("hr"));

        return registro;
    }

    private SideNav createNavegation2(){
        SideNav nav = new SideNav();

        nav.addItem(
                new SideNavItem("TarefasGerais", TarefasGeraisView.class, VaadinIcon.NOTEBOOK.create()));
        nav.addItem(new SideNavItem("NovoUsuario", NovoUsuarioView.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem("CriarPerfil", CriarPerfilView.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem("TarefasGeraisConcluido", TarefasGeraisConcluidoView.class, VaadinIcon.NOTEBOOK.create()));
        nav.addItem(new SideNavItem("AddTarefasGerais", AddTarefasGeraisView.class, VaadinIcon.NOTEBOOK.create()));
        
        nav.getElement().appendChild(new Element("hr"));

        nav.addItem(
            new SideNavItem("PerfilUsuario", PerfilUsuarioView.class, LineAwesomeIcon.USER.create()));
        
        return nav;

    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
