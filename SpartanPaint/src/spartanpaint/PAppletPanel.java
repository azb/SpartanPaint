package spartanpaint;

import java.awt.Dimension;
import javax.swing.JPanel;

import processing.core.PApplet;

public abstract class PAppletPanel<T extends PApplet> extends JPanel
{
    protected T Instance;
    public T GetInstance()
    {
        return Instance;
    }
    
    protected abstract T CreatePApplet();

    public PAppletPanel()
    {
        this(true);
    }
    
    public PAppletPanel(boolean auto_resize)
    {
        // This is really annoying but at least it works.
        // I don't know of any better way to make the
        // layout recalculate where things should be.
        java.awt.EventQueue.invokeLater(() ->
        {
            Instance = CreatePApplet();
            if(Instance != null)
            {
                Instance.init();
                if(auto_resize)
                {
                    java.awt.EventQueue.invokeLater(() ->
                    {
                        Dimension preferred_size = getPreferredSize();
                        Instance.size(
                                preferred_size.width,
                                preferred_size.height);
                        java.awt.EventQueue.invokeLater(() -> {
                            add(Instance);
                        });
                    });
                }
            }
        });
    }
}
