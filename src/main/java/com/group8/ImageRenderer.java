package com.group8;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ImageRenderer extends JLabel implements TableCellRenderer {

    private int maxWidth;
    private int maxHeight;

    public ImageRenderer(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) value;
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        } else {
            setIcon(null);
        }
        return this;
    }
}
