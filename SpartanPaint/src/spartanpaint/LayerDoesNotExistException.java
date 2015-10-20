/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;

/**
 *  Throw this if a layer doesn't exist.
 * @author Christian
 */
public class LayerDoesNotExistException extends RuntimeException{
    public LayerDoesNotExistException()
    {
        super("Specified layer does not exist within the cuurent context. Action has not been completed.");
    }
}
