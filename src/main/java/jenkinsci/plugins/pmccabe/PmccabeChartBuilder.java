package jenkinsci.plugins.pmccabe;

import java.awt.Color;

import jenkinsci.plugins.pmccabe.utils.PmccabeReport;
import jenkinsci.plugins.pmccabe.util.Messages;
//import hudson.cli.client.Messages;
import hudson.util.ChartUtil.NumberOnlyBuildLabel;
import hudson.util.DataSetBuilder;
import hudson.util.Graph;
import hudson.util.ShiftedCategoryAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.DefaultCategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;



public class PmccabeChartBuilder extends Graph {

    private PmccabeAction action;

    protected PmccabeChartBuilder(PmccabeAction action, int width, int height) {
        super(action.getBuild().getTimestamp(), width, height);
        this.action = action;
    }

    /**
     * Creates a PMcCabe trend graph
     *
     * @return the JFreeChart graph object
     */
    protected JFreeChart createGraph() {


        JFreeChart chart = ChartFactory.createStackedAreaChart(null, null, Messages.PmccabeAction_LinesOfCode(), 
        		buildDataset(action), PlotOrientation.VERTICAL, true, false, true);

        chart.setBackgroundPaint(Color.white);


        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlinePaint(null);
        plot.setForegroundAlpha(0.8f);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.black);


        CategoryAxis domainAxis = new ShiftedCategoryAxis(null);
        plot.setDomainAxis(domainAxis);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        domainAxis.setCategoryMargin(0.0);


        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // crop extra space around the graph
        plot.setInsets(new RectangleInsets(0, 0, 0, 5.0));


        CategoryItemRenderer firstRender = new DefaultCategoryItemRenderer();
        plot.setRenderer(firstRender);


        //Second
        NumberAxis axis2 = new NumberAxis("McCabe Complexity");
        axis2.setLabelPaint(Color.BLUE);
        axis2.setAxisLinePaint(Color.BLUE);
        axis2.setTickLabelPaint(Color.BLUE);
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeAxis(1, axis2);
        //CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
        categoryPlot.setDataset(1, buildMcCabeDataset(action));
        categoryPlot.mapDatasetToRangeAxis(1, 1);
        CategoryItemRenderer rendu = new DefaultCategoryItemRenderer();
        rendu.setBasePaint(Color.BLUE);
        categoryPlot.setRenderer(1, rendu);


        //Third
        NumberAxis axis3 = new NumberAxis(Messages.PmccabeAction_NumberOfFiles());
        axis3.setLabelPaint(Color.GREEN);
        axis3.setAxisLinePaint(Color.GREEN);
        axis3.setTickLabelPaint(Color.GREEN);
        CategoryPlot categoryPlot3 = chart.getCategoryPlot();
        categoryPlot3.setRangeAxis(2, axis3);
        categoryPlot3.setDataset(2, buildFilesDataset(action));
        categoryPlot3.mapDatasetToRangeAxis(2, 2);
        categoryPlot3.mapDatasetToDomainAxis(2, 0);
        CategoryItemRenderer rendu3 = new DefaultCategoryItemRenderer();
        rendu3.setBasePaint(Color.GREEN);
        categoryPlot3.setRenderer(2, rendu3);

        return chart;
    }

    private static CategoryDataset buildDataset(PmccabeAction lastAction) {
        DataSetBuilder<String, NumberOnlyBuildLabel> builder = new DataSetBuilder<String, NumberOnlyBuildLabel>();

        PmccabeAction action = lastAction;
        do {
            PmccabeReport report = action.getReport();
            if (report != null) {
                
                NumberOnlyBuildLabel buildLabel = new NumberOnlyBuildLabel(action.getBuild());

                builder.add(report.getLinesOfCode(), Messages.PmccabeAction_LinesOfCode(), buildLabel);
            }
            action = action.getPreviousAction();
        } while (action != null);

        return builder.build();
    }

    private static CategoryDataset buildMcCabeDataset(PmccabeAction lastAction) {
        DataSetBuilder<String, NumberOnlyBuildLabel> builder = new DataSetBuilder<String, NumberOnlyBuildLabel>();

        PmccabeAction action = lastAction;
        do {
            PmccabeReport report = action.getReport();
            if (report != null) {
                
                NumberOnlyBuildLabel buildLabel = new NumberOnlyBuildLabel(action.getBuild());

                builder.add(report.getModifiedComplexity(), Messages.PmccabeAction_ModifiedComplexity(), buildLabel);
            }
            action = action.getPreviousAction();
        } while (action != null);

        return builder.build();
    }

    private static CategoryDataset buildFilesDataset(PmccabeAction lastAction) {
        DataSetBuilder<String, NumberOnlyBuildLabel> builder = new DataSetBuilder<String, NumberOnlyBuildLabel>();

        PmccabeAction action = lastAction;
        do {
            PmccabeReport report = action.getReport();
            if (report != null) {
                
                NumberOnlyBuildLabel buildLabel = new NumberOnlyBuildLabel(action.getBuild());

                builder.add(report.getNumberOfFiles(), Messages.PmccabeAction_NumberOfFiles(), buildLabel);
            }
            action = action.getPreviousAction();
        } while (action != null);

        return builder.build();
    }
}
