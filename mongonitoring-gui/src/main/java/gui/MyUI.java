package gui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import gui.monitoringViews.Chart;
import monitoring.App;

import javax.servlet.annotation.WebServlet;

import static gui.Setup.setup;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    private Navigator navigator;
    private MenuManager menuManager;
    protected static final String MAINVIEW = "main";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            //todo: quartz
            App.main(new String[0]);
        } catch (java.io.IOException e){
                System.out.println(e.getMessage());
        }
        ComponentContainer cc = setup(new VerticalLayout(), VerticalLayout::setSizeFull);
        navigator = new Navigator(this, new Navigator.ComponentContainerViewDisplay(cc));

        navigator.addView("", new MainView(""));
        navigator.addView("test1", new Chart());
        navigator.addView("test2", new MainView("test2"));
        menuManager = new MenuManager(navigator, cc );

        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        setContent(menuManager);
//
//        DataSeries dataSeries = new DataSeries()
//                .add(2, 6, 7, 10);
//
//        SeriesDefaults seriesDefaults = new SeriesDefaults()
//                .setRenderer(SeriesRenderers.BAR);
//
//        Axes axes = new Axes()
//                .addAxis(
//                        new XYaxis()
//                                .setRenderer(AxisRenderers.CATEGORY)
//                                .setTicks(
//                                        new Ticks()
//                                                .add("a", "b", "c", "d")));
//
//        Highlighter highlighter = new Highlighter()
//                .setShow(false);
//
//        Options options = new Options()
//                .setSeriesDefaults(seriesDefaults)
//                .setAxes(axes)
//                .setHighlighter(highlighter);
//
//        DCharts chart = new DCharts()
//                .setDataSeries(dataSeries)
//                .setOptions(options)
//                .show();

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
