/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package meter_rpm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;

/**
 *
 * @author deh
 */
public class Stackoverflow {

//  private static final int DISPLAY_MAX = 6000;
  private final DefaultValueDataset dataset0 = new DefaultValueDataset();
  private final DefaultValueDataset dataset1 = new DefaultValueDataset();
  private final JFrame frame = new JFrame();

    /**
     *
     * @param valinit
     * @param title
     */
    public Stackoverflow(int valinit, String title) {
    //frame.setPreferredSize(new Dimension(300, 300));
    frame.add(buildDialPlot(0, 6000, 500));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle(title);


    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        frame.setVisible(true);
      }
    });
  }

  public void setValue(int value0, int value1) {
    dataset0.setValue(value0);
    dataset1.setValue(value1);
    }

    /**
     *
     * @param d0 Outer pointer value
     * @param d1 Inner pointer value
     */
    public void setValue(double d0, double d1) {
    dataset0.setValue(d0);
    dataset1.setValue(d1);
    }

  private ChartPanel buildDialPlot(int minimumValue, int maximumValue,
      int majorTickGap) {

    DialPlot plot = new DialPlot();
    plot.setView(0.0D, 0.0D, 1.0D, 1.0D);
    plot.setDataset(0, dataset0);
    plot.setDataset(1, dataset1);
    
    plot.setDialFrame(new StandardDialFrame());
    
    
    DialTextAnnotation dialtextannotation = new DialTextAnnotation("RPM");
                        dialtextannotation.setFont(new Font("Dialog", 1, 16));
                        dialtextannotation.setRadius(0.69999999999999996D);
                        plot.addLayer(dialtextannotation);
    
    // value indicator uses the real data set
    //plot.addLayer(new DialValueIndicator(0));
    
    DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
                        dialvalueindicator.setFont(new Font("Dialog",Font.BOLD, 12));
                        dialvalueindicator.setOutlinePaint(Color.YELLOW);
                        dialvalueindicator.setRadius(0.6D);
                        dialvalueindicator.setAngle(-90D);
                        dialvalueindicator.setTemplateValue(1000);
                        plot.addLayer(dialvalueindicator);
                        
                        org.jfree.chart.plot.dial.DialPointer.Pin pin1 = new org.jfree.chart.plot.dial.DialPointer.Pin(1);
                        pin1.setRadius(0.55000000000000004D);
                        plot.addPointer(pin1);
                        
    // needle uses constrained data set
    plot.addLayer(new DialPointer.Pointer(0));

    StandardDialScale scale = new StandardDialScale(0d, 6000, -110, -320, majorTickGap, 9);
        scale.setTickRadius(0.88);
        scale.setTickLabelOffset(0.20);
        scale.setTickLabelFormatter(new DecimalFormat ("####"));

        
        plot.addScale(0, scale);
    
    StandardDialScale standarddialscale1 = new StandardDialScale(0D, 30D, -120D, -300D, 5D, 4);
                        standarddialscale1.setTickRadius(0.5D);
                        standarddialscale1.setTickLabelOffset(0.14999999999999999D);
                        standarddialscale1.setTickLabelFont(new Font("Dialog", 0, 10));
                        standarddialscale1.setMajorTickPaint(Color.RED);
                        standarddialscale1.setMinorTickPaint(Color.RED);
                        plot.addScale(1, standarddialscale1);
                        
                        plot.mapDatasetToScale(1, 1);
                        
    JFreeChart jfreechart = new JFreeChart(plot);
                        jfreechart.setTitle("ENGINE RPM & MANIFOLD PRESSURE");
                        ChartPanel chartpanel = new ChartPanel(jfreechart);
                        chartpanel.setPreferredSize(new Dimension(400, 400));
                        
    
    return chartpanel;
  }
}
