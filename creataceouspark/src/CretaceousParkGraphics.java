import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class CretaceousParkGraphics extends Graphics {
    Graphics g;

    @Override
    public Graphics create() {
        return g.create();
    }

    @Override
    public void translate(int i, int i1) {
        g.translate(i, i1);
    }

    @Override
    public Color getColor() {
        return g.getColor();
    }

    @Override
    public void setColor(Color color) {
        g.setColor(color);
    }

    @Override
    public void setPaintMode() {
        g.setPaintMode();
    }

    @Override
    public void setXORMode(Color color) {
        g.setXORMode(color);
    }

    @Override
    public Font getFont() {
        return g.getFont();
    }

    @Override
    public void setFont(Font font) {
        g.setFont(font);
    }

    @Override
    public FontMetrics getFontMetrics(Font font) {
        return g.getFontMetrics(font);
    }

    @Override
    public Rectangle getClipBounds() {
        return g.getClipBounds();
    }

    @Override
    public void clipRect(int i, int i1, int i2, int i3) {
        g.clipRect(i, i1, i2, i3);
    }

    @Override
    public void setClip(int i, int i1, int i2, int i3) {
        g.setClip(i, i1, i2, i3);
    }

    @Override
    public Shape getClip() {
        return g.getClip();
    }

    @Override
    public void setClip(Shape shape) {
        g.setClip(shape);
    }

    @Override
    public void copyArea(int i, int i1, int i2, int i3, int i4, int i5) {
        g.copyArea(i, i1, i2, i3, i4, i5);
    }

    @Override
    public void drawLine(int i, int i1, int i2, int i3) {
        g.drawLine(i, i1, i2, i3);
    }

    @Override
    public void fillRect(int i, int i1, int i2, int i3) {
        g.fillRect(i, i1, i2, i3);
    }

    @Override
    public void clearRect(int i, int i1, int i2, int i3) {
        g.clearRect(i, i1, i2, i3);
    }

    @Override
    public void drawRoundRect(int i, int i1, int i2, int i3, int i4, int i5) {
        g.drawRoundRect(i, i1, i2, i3, i4, i5);
    }

    @Override
    public void fillRoundRect(int i, int i1, int i2, int i3, int i4, int i5) {
        g.fillRoundRect(i, i1, i2, i3, i4, i5);
    }

    @Override
    public void drawOval(int i, int i1, int i2, int i3) {
        g.drawOval(i, i1, i2, i3);
    }

    @Override
    public void fillOval(int i, int i1, int i2, int i3) {
        g.fillOval(i, i1, i2, i3);
    }

    @Override
    public void drawArc(int i, int i1, int i2, int i3, int i4, int i5) {
        g.drawArc(i, i1, i2, i3, i4, i5);
    }

    @Override
    public void fillArc(int i, int i1, int i2, int i3, int i4, int i5) {
        g.fillArc(i, i1, i2, i3, i4, i5);
    }

    @Override
    public void drawPolyline(int[] ints, int[] ints1, int i) {
        g.drawPolyline(ints, ints1, i);
    }

    @Override
    public void drawPolygon(int[] ints, int[] ints1, int i) {
        g.drawPolygon(ints, ints1, i);
    }

    @Override
    public void fillPolygon(int[] ints, int[] ints1, int i) {
        g.fillPolygon(ints, ints1, i);
    }

    @Override
    public void drawString(String s, int i, int i1) {
        g.drawString(s, i, i1);
    }

    @Override
    public void drawString(AttributedCharacterIterator attributedCharacterIterator, int i, int i1) {
        g.drawString(attributedCharacterIterator, i, i1);
    }

    @Override
    public boolean drawImage(Image image, int i, int i1, ImageObserver imageObserver) {
        return g.drawImage(image, i, i1, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int i, int i1, int i2, int i3, ImageObserver imageObserver) {
        return g.drawImage(image, i, i1, i2, i3, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int i, int i1, Color color, ImageObserver imageObserver) {
        return g.drawImage(image, i, i1, color, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int i, int i1, int i2, int i3, Color color, ImageObserver imageObserver) {
        return g.drawImage(image, i, i1, i2, i3, color, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, ImageObserver imageObserver) {
        return g.drawImage(image, i, i1, i2, i3, i4, i5, i6, i7, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, Color color, ImageObserver imageObserver) {
        return g.drawImage(image, i, i1, i2, i3, i4, i5, i6, i7, color, imageObserver);
    }

    @Override
    public void dispose() {
        g.dispose();
    }
}
