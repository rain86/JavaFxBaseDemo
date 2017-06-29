/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

/**
 *
 * @author mile
 * 每个控制类都要实现这个接口，让他有application的引用，从而控制其他地方
 */
public interface setApp {
    public void setApp(Hospital application);
}