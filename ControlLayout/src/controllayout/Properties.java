package controllayout;

import java.awt.Component;
import java.awt.Container;

/**
 * Represents the properties of a component belonging to the layout.
 * 
 * @author Gilmar Thomas de Araújo Júnior
 *
 */
public class Properties {
	private int xParts, xInitial, xFinal, yParts, yInitial, yFinal;

	private boolean xResizable, yResizable;
	private int xAlignment, yAlignment, containerInitialWidth, containerInitialHeight;
	private boolean componentSizeLimits;

	public static final int CENTRAL = 0, LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;

	public Properties(int xParts, int xInitial, int xFinal, int yParts, int yInitial, int yFinal) {
		this(null, xParts, xInitial, xFinal, yParts, yInitial, yFinal, true, 0, true, 0, false);
	}

	public Properties(Container container, int xParts, int xInitial, int xFinal, int yParts, int yInitial, int yFinal,
			boolean xResizable, int xAlignment, boolean yRedimensionavel, int yAlignment) {
		this(container, xParts, xInitial, xFinal, yParts, yInitial, yFinal, xResizable, xAlignment, yRedimensionavel, yAlignment, false);
	}
	
	public Properties(Container container, int xParts, int xInitial, int xFinal, int yParts, int yInitial, int yFinal,
			boolean xResizable, int xAlignment, boolean yRedimensionavel, int yAlignment, boolean componentSizeLimits)
	{
		this.xParts = xParts;
		this.xInitial = xInitial;
		this.xFinal = xFinal;
		this.yParts = yParts;
		this.yInitial = yInitial;
		this.yFinal = yFinal;
		this.xResizable = xResizable;
		this.xAlignment = xAlignment;
		this.yResizable = yRedimensionavel;
		this.yAlignment = yAlignment;

		if(container != null)
		{
			this.containerInitialWidth = container.getWidth();
			this.containerInitialHeight = container.getHeight();
		}
		
		this.componentSizeLimits = componentSizeLimits;
	}

	public int getXParts() {
		return xParts;
	}

	public int getInitialX() {
		return xInitial;
	}

	public int getXFinal() {
		return xFinal;
	}

	public int getYParts() {
		return yParts;
	}

	public int getYInitial() {
		return yInitial;
	}

	public int getYFinal() {
		return yFinal;
	}

	public boolean isXResizable() {
		return xResizable;
	}

	public boolean isYResizable() {
		return yResizable;
	}

	public int getXAlignment() {
		return xAlignment;
	}

	public int getYAligniment() {
		return yAlignment;
	}

	public int getContainerInitialWidth() {
		return containerInitialWidth;
	}

	public int getContainerInitialHeight() {
		return containerInitialHeight;
	}

	private double initialWidth() {
		return (double) containerInitialWidth / xParts * (xFinal - xInitial);
	}

	private double initialHeight() {
		return (double) containerInitialHeight / yParts * (yFinal - yInitial);
	}

	private double calculateHorizontalDisplacement(double containerWidth, int componentWidth) {
		return containerWidth / getXParts() * (getXFinal() - getInitialX()) - componentWidth;
	}

	private double calculateVerticalDisplacement(double containerHeight, int componentHeight) {
		return containerHeight / getYParts() * (getYFinal() - getYInitial()) - componentHeight;
	}

	/*
	 * public void posicionar(Component component,Container container) { if
	 * (!isXRedimensionavel() && !isYRedimensionavel()) { component.setSize( getW()
	 * / getPartesX() (getXFinal() - getXInicial()), getH() / getPartesY()
	 * (getYFinal() - getYInicial()));
	 * 
	 * int d;//deslocamento
	 * 
	 * int x = 0; int y = 0; switch (getAlinhamentoXNR()) { case Properties.CENTRAL:
	 * x = calculateHorizontalDisplacement(container.getWidth()) / 2 + getXInicial()
	 * * container.getWidth() / getPartesX(); break; case Properties.ESQUERDA: x =
	 * getXInicial() * container.getWidth() / getPartesX(); break; case
	 * Properties.DIREITA: x = getXFinal() * container.getWidth() / getPartesX() -
	 * component.getWidth(); break; default: x =
	 * calculateHorizontalDisplacement(container.getWidth()) / 2 + getXInicial() *
	 * container.getWidth() / getPartesX(); } switch (getAlinhamentoYNR()) { case
	 * Properties.CENTRAL: y = calculateVerticalDisplacement(container.getHeight())
	 * / 2 + getYInicial() * container.getHeight() / getPartesY(); break; case
	 * Properties.ACIMA: y = getYInicial() * container.getHeight() / getPartesY();
	 * break; case Properties.ABAIXO: y = getYFinal() * container.getHeight() /
	 * getPartesY() - component.getHeight(); break; default: y =
	 * calculateHorizontalDisplacement(container.getHeight()) / 2 + getYInicial() *
	 * container.getHeight() / getPartesY(); } component.setLocation(x, y); } else
	 * if (!isXRedimensionavel()) { component.setSize( getW() / getPartesX()
	 * (getXFinal() - getXInicial()), container.getHeight() / getPartesY()
	 * (getYFinal() - getYInicial())); int x = 0; int y = 0; switch
	 * (getAlinhamentoXNR()) { case Properties.CENTRAL: x =
	 * calculateHorizontalDisplacement(container.getWidth()) / 2 + getXInicial() *
	 * container.getWidth() / getPartesX(); break; case Properties.ESQUERDA: x =
	 * getXInicial() * container.getWidth() / getPartesX(); break; case
	 * Properties.DIREITA: x = getXFinal() * container.getWidth() / getPartesX() -
	 * component.getWidth(); break; default: x =
	 * calculateHorizontalDisplacement(container.getWidth()) / 2 + getXInicial() *
	 * container.getWidth() / getPartesX(); }
	 * 
	 * y = container.getHeight() / getPartesY() getYInicial();
	 * component.setLocation(x, y); } else if (!isYRedimensionavel()) {
	 * component.setSize( container.getWidth() / getPartesX() (getXFinal() -
	 * getXInicial()), getH() / getPartesY() (getYFinal() - getYInicial())); int x =
	 * 0; int y = 0; x = container.getWidth() / getPartesX() (getXFinal() -
	 * getXInicial()) / 2 + getXInicial() * getPartesX() - component.getWidth() / 2;
	 * switch (getAlinhamentoYNR()) { case Properties.CENTRAL: y =
	 * calculateVerticalDisplacement(container.getHeight()) / 2 + getYInicial() *
	 * container.getHeight() / getPartesY(); break; case Properties.ACIMA: y =
	 * getYInicial() * container.getHeight() / getPartesY(); break; case
	 * Properties.ABAIXO: y = getYFinal() * container.getHeight() / getPartesY() -
	 * component.getHeight(); break; default: y =
	 * calculateHorizontalDisplacement(container.getHeight()) / 2 + getYInicial() *
	 * container.getHeight() / getPartesY(); } x = container.getWidth() /
	 * getPartesX() getXInicial(); component.setLocation(x, y); } else {
	 * component.setLocation( container.getWidth() / getPartesX() getXInicial(),
	 * container.getHeight() / getPartesY() getYInicial());
	 * 
	 * component.setSize( container.getWidth() / getPartesX() (getXFinal() -
	 * getXInicial()), container.getHeight() / getPartesY() (getYFinal() -
	 * getYInicial())); } }
	 */

	public void toPosition(Component component, Container container) {
		int width = 0;
		int height = 0;
		int x = 0, y = 0;

		if (!isXResizable()) {
			width = (int) initialWidth();
		} else {
			width = container.getWidth() / getXParts() * (getXFinal() - getInitialX());
		}

		if (!isYResizable()) {
			height = (int) initialHeight();
		} else {
			height = container.getHeight() / getYParts() * (getYFinal() - getYInitial());
		}

		if(this.componentSizeLimits)
		{
		if (width < component.getMinimumSize().width)
			width = component.getMinimumSize().width;
		else if (width > component.getMaximumSize().width)
			width = component.getMaximumSize().width;

		if (height < component.getMinimumSize().height)
			height = component.getMinimumSize().height;
		else if (height > component.getMaximumSize().height)
			height = component.getMaximumSize().height;
		}
		
		switch (getXAlignment()) {
		case Properties.LEFT:
			x = this.xInitial * container.getWidth() / this.xParts;
			break;
		case Properties.RIGHT:
			x = this.xFinal * container.getWidth() / this.xParts - component.getWidth();
			break;
		case Properties.CENTRAL:
		default:
			x = (int)(calculateHorizontalDisplacement(container.getWidth(), width) / 2
					+ (double)this.xInitial * container.getWidth() / this.xParts);
		}

		switch (getYAligniment()) {
		case Properties.TOP:
			y = this.yInitial * container.getHeight() / this.yParts;
			break;
		case Properties.BOTTOM:
			y = this.yFinal * container.getHeight() / this.yParts - component.getHeight();
			break;
		case Properties.CENTRAL:
		default:
			y = (int)(calculateVerticalDisplacement(container.getHeight(), height) / 2
					+ (double)this.yInitial * container.getHeight() / this.yParts);
		}

		component.setSize(width, height);
		component.setLocation(x, y);
	}
}
