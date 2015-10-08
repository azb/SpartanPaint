package spartanpaint;

import java.awt.Dimension;
import javax.swing.JPanel;

import processing.core.PApplet;

public class PAppletPanel<T extends PApplet> extends JPanel
{
    private T Instance;

    public T GetInstance()
    {
        return Instance;
    }

    public void SetInstance(T instance, boolean auto_resize)
    {
        Instance = instance;
        if(Instance != null)
        {
            Instance.init();
            if(auto_resize)
            {
                // This is really annoying but at least it works.
                java.awt.EventQueue.invokeLater(() ->
                {
                    Dimension prefered_size = getPreferredSize();
                    Instance.size(prefered_size.width, prefered_size.height);
                    java.awt.EventQueue.invokeLater(() ->
                    {
                        add(Instance);
                    });
                });
            }
        }
    }
}
