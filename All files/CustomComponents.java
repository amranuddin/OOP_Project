package com.mycompany.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

class MixedGradientPanel extends JPanel {
    private Color colorStart;
    private Color colorMid;
    private Color colorEnd;

    public MixedGradientPanel(Color start, Color mid, Color end) {
        this.colorStart = start;
        this.colorMid = mid;
        this.colorEnd = end;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        LinearGradientPaint lgp = new LinearGradientPaint(
                0, 0, width, height,
                new float[]{0.0f, 0.5f, 1.0f},
                new Color[]{colorStart, colorMid, colorEnd}
        );

        g2.setPaint(lgp);
        g2.fillRect(0, 0, width, height);
    }
}

class DashboardChartPanel extends JPanel {

    private final HospitalDashboardState state;

    private static final Color COLOR_BLUE_LIGHT = new Color(37, 99, 235);
    private static final Color COLOR_VIOLET_LIGHT = new Color(147, 51, 234);
    private static final Color COLOR_CYAN_LIGHT = new Color(13, 148, 136);
    private static final Color COLOR_TEXT = new Color(51, 65, 85);

    public DashboardChartPanel(HospitalDashboardState state) {
        this.state = state;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        int padding = 40;
        int graphHeight = height - (2 * padding) - 30;

        g2.setColor(new Color(203, 213, 225));
        g2.drawLine(padding, height - padding - 20, width - padding, height - padding - 20);
        g2.drawLine(padding, padding, padding, height - padding - 20);

        int maxCapacity = Math.max(state.getTotalCabins(), state.getTotalBeds());
        if (maxCapacity == 0) maxCapacity = 1;

        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
        g2.setColor(COLOR_TEXT);

        drawBarGroup(g2, padding + 40, height - padding - 20, graphHeight, maxCapacity, 
                     "Cabins", state.getTotalCabins(), state.getOccupiedCabins(), state.getAvailableCabins());

        drawBarGroup(g2, padding + 220, height - padding - 20, graphHeight, maxCapacity, 
                     "Ward Beds", state.getTotalBeds(), state.getOccupiedBeds(), state.getAvailableBeds());

        drawLegend(g2, width - 180, 20);
    }

    private void drawBarGroup(Graphics2D g2, int startX, int baseY, int graphHeight, int maxVal, 
                              String title, int total, int booked, int avail) {
        int barWidth = 28;

        int totalBarH = (int) (((double) total / maxVal) * (graphHeight - 20));
        int bookedBarH = (int) (((double) booked / maxVal) * (graphHeight - 20));
        int availBarH = (int) (((double) avail / maxVal) * (graphHeight - 20));

        g2.setColor(COLOR_BLUE_LIGHT);
        g2.fillRect(startX, baseY - totalBarH, barWidth, totalBarH);
        g2.drawString(String.valueOf(total), startX + 8, baseY - totalBarH - 5);

        g2.setColor(COLOR_VIOLET_LIGHT);
        g2.fillRect(startX + barWidth + 6, baseY - bookedBarH, barWidth, bookedBarH);
        g2.drawString(String.valueOf(booked), startX + barWidth + 14, baseY - bookedBarH - 5);

        g2.setColor(COLOR_CYAN_LIGHT);
        g2.fillRect(startX + (barWidth * 2) + 12, baseY - availBarH, barWidth, availBarH);
        g2.drawString(String.valueOf(avail), startX + (barWidth * 2) + 20, baseY - availBarH - 5);

        g2.setColor(COLOR_TEXT);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        g2.drawString(title, startX + 15, baseY + 20);
    }

    private void drawLegend(Graphics2D g2, int x, int y) {
        g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        g2.setColor(COLOR_BLUE_LIGHT);
        g2.fillRect(x, y, 12, 12);
        g2.setColor(COLOR_TEXT);
        g2.drawString("Total", x + 18, y + 10);

        g2.setColor(COLOR_VIOLET_LIGHT);
        g2.fillRect(x, y + 18, 12, 12);
        g2.setColor(COLOR_TEXT);
        g2.drawString("Booked", x + 18, y + 28);

        g2.setColor(COLOR_CYAN_LIGHT);
        g2.fillRect(x, y + 36, 12, 12);
        g2.setColor(COLOR_TEXT);
        g2.drawString("Available", x + 18, y + 46);
    }
}

class KpiCardPanel extends JPanel {

    public KpiCardPanel(String title, JLabel valueLabel, Color accentColor, Color bgColor) {
        setLayout(new BorderLayout(10, 10));
        setBackground(bgColor);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accentColor, 1),
                " " + title + " ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                accentColor
        ));

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        valueLabel.setForeground(accentColor);

        add(valueLabel, BorderLayout.CENTER);
    }
}