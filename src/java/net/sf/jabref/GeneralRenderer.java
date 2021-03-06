/*  Copyright (C) 2003-2011 JabRef contributors.
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/
package net.sf.jabref;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Renderer for table cells, which supports both Icons, JLabels and plain text.
 */
public class GeneralRenderer /*extends JTable implements TableCellRenderer {*/ extends DefaultTableCellRenderer {

    Color background, selBackground = null;

    public GeneralRenderer(Color c) {
        super();
        this.background = c;
        setBackground(c);
    }


    /**
     * Renderer with specified foreground and background colors, and default selected
     * background color.
     * @param c Foreground color
     * @param fg Background color
     */
    public GeneralRenderer(Color c, Color fg) {
        this(c);
        this.background = c;
        setForeground(fg);
    }

    /**
     * Renderer with specified foreground, background and selected background colors
     * @param c Foreground color
     * @param fg Unselected background color
     * @param sel Selected background color
     */
    public GeneralRenderer(Color c, Color fg, Color sel) {
        this(c);
        this.background = c;
        setForeground(fg);
        this.selBackground = sel;
    }

    public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if (selBackground == null)
            return super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
        else {
            Component c = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
            if (isSelected)
                c.setBackground(selBackground);
            else
                c.setBackground(background);
            return c;
        }
    }

    public void firePropertyChange(String propertyName, boolean old, boolean newV) {}
    public void firePropertyChange(String propertyName, Object old, Object newV) {}

    /* For enabling the renderer to handle icons. */
    protected void setValue(Object value) {
        //System.out.println(""+value);
        if (value instanceof Icon) {
            setIcon((Icon)value);
            setText(null);
            //super.setValue(null);
        } else if (value instanceof JLabel) {
          JLabel lab = (JLabel)value;
          setIcon(lab.getIcon());
          //table.setToolTipText(lab.getToolTipText());
          setToolTipText(lab.getToolTipText());
          if (lab.getIcon() != null)
            setText(null);
        } else {

            setIcon(null);
            //table.setToolTipText(null);
            setToolTipText(null);
            if (value != null)
                setText(value.toString());
            else
                setText(null);
        }
    }

    /*  public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        //System.out.println(antialiasing);
        if (antialiasing) {
            RenderingHints rh = g2.getRenderingHints();
            rh.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHints(rh);
        }
          super.paint(g2);

    }*/

}
