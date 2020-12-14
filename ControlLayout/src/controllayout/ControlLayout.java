package controllayout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.ArrayList;

/**
 * Represents the Control Layout
 * 
 * @author Gilmar Thomas de Araújo Júnior
 *
 */
public class ControlLayout implements LayoutManager2
{

	ArrayList<Properties> propriedades = new ArrayList<Properties>();

	@Override
	public void addLayoutComponent(String arg0, Component arg1)
	{
		//Not implemented by this layout
	}

	@Override
	public void layoutContainer(Container container)
	{
		// TODO Auto-generated method stub
		for (int n = 0; n < container.getComponents().length; n++)
		{
			Properties propriedade = propriedades.get(n);
			Component component = container.getComponent(n);
			if (propriedade != null)
			{
				propriedade.toPosition(component,container);
			}
		}
	}

	@Override
	public Dimension minimumLayoutSize(Container container)
	{
		return container.getMinimumSize();
	}

	@Override
	public Dimension preferredLayoutSize(Container container)
	{
		return container.getPreferredSize();
	}

	@Override
	public void removeLayoutComponent(Component component)
	{
		// TODO Auto-generated method stub
		for (int n = 0; n < component.getParent().getComponentCount(); n++)
		{
			if (component == component.getParent().getComponent(n))
			{
				propriedades.remove(n);
				break;
			}
		}
	}

	@Override
	public void addLayoutComponent(Component component, Object object)
	{
		// TODO Auto-generated method stub
		if (object instanceof Properties)
		{
			propriedades.add((Properties) object);
		}
		else
			propriedades.add(null);
	}

	@Override
	public float getLayoutAlignmentX(Container arg0)
	{
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container arg0)
	{
		return 0;
	}

	@Override
	public void invalidateLayout(Container arg0)
	{

	}

	@Override
	public Dimension maximumLayoutSize(Container container)
	{
		return container.getMaximumSize();
	}

}
