/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;
import java.util.ArrayList;
/**
 * Handles and encapsulates Layer information in the Canvas. Contains a list of layers existing within the canvas., as well as backups of deleted Layers.
 * @author Christian
 */
public class LayerContainer {
    private ArrayList<Layer> layers;
    private ArrayList<Layer> backups;
    private final String DEFAULT_NEW_LAYER_NAME = "Layer";
    public LayerContainer()
    {
        layers = new ArrayList<>();
        backups = new ArrayList<>();
    }
    /**
     * Creates a new layer and appends it to the end of the list.
     * @param x the horizontal length of the layer in pixels.
     * @param y the vertical height of the layer in pixels.
     * @return the index of the layer.
     */
    public int newLayer(int x, int y)
    {
        Layer layer = new Layer(x,y,(DEFAULT_NEW_LAYER_NAME + layers.size()));
        layers.add(layer);
        return layers.indexOf(layer);
    }
    /**
     * Rearranges the specified layer into a new position within the list of layers.
     * @param initialIndex
     * @param newIndex 
     */
    public void rearrange(int initialIndex, int newIndex)
    {
        checkIfLayerExists(initialIndex);
        layers.add(newIndex, layers.remove(initialIndex));
    }
        
    /**
     * Deletes the layer from the program. A backup is kept.
     * @param index the index of the layer to delete.
     */
    public void deleteLayer(int index)
    {
        checkIfLayerExists(index);
        backups.add(layers.remove(index));
    }
    /**
     * Gets the Layer object in the specified index
     * @param index the index of the layer
     * @return 
     */
    public Layer getLayer(int index)
    {
        checkIfLayerExists(index);
        return layers.get(index);
    }
    public void setLayer(int index, Layer layer)
    {
        checkIfLayerExists(index);
        layers.set(index, layer);
    }
    public int size()
    {
        return layers.size();
    }
    private boolean checkIfLayerExists(int index)
    {
        if(index > layers.size())
        {
            throw new LayerDoesNotExistException();
        }
        else if (layers.get(index) == null)
        {
            throw new LayerDoesNotExistException();
        }
        return true;
    }
   
    /*
     * PERMENANTLY deletes the layer and any information it contains. No backups are made. Make sure you absolutely want to do this!
     * @param index 
     * @return if the layer was successfully incenerated.
     *
    public boolean incenerateLayer(int index)
    {
        if(index > layers.size())
        {
            return false;
        }
        else if (layers.get(index) == null)
        {
            return false;
        }
        Layer l = layers.get(index);
        
        incenerateLayer(l);
        layers.remove(index);
        return true;
    }
    
    private static void incenerateLayer(Layer layer)
    {
        for(int i = 0; i < l.length; i++)
        {
            for(int j = 0; j < l.height; j++)
            {
                layer.setPixel(i, j, null);
            }
        }
        
    }
    */
}
