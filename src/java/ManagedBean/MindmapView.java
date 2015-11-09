/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import java.io.Serializable;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

/**
 *
 * @author CRAVELO
 */
@ManagedBean
public class MindmapView implements Serializable {
    private static final long serialVersionUID = -8649679148276691473L;
    
    private MindmapNode root;
     
    private MindmapNode selectedNode;
     
    public MindmapView() {
        root = new DefaultMindmapNode("Unidad1", "Google WebSite", "FFBF00", false);
         
        MindmapNode ips = new DefaultMindmapNode("topico1", "IP Numbers", "58FA58", true);
        MindmapNode ns = new DefaultMindmapNode("topico 2", "Namespaces", "58FA58", true);
        MindmapNode malware = new DefaultMindmapNode("topico 3", "Malicious Software", "FF4000", true);
         
        root.addNode(ips);
        root.addNode(ns);
        root.addNode(malware);
    }
 
    public MindmapNode getRoot() {
        return root;
    }
 
    public MindmapNode getSelectedNode() {
        return selectedNode;
    }
    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }
 
    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();
         
        //populate if not already loaded
        if(node.getChildren().isEmpty()) {
            Object label = node.getLabel();
 
            if(label.equals("NS(s)")) {
                for(int i = 0; i < 25; i++) {
                    node.addNode(new DefaultMindmapNode("ns" + i + ".google.com", "Namespace " + i + " of Google", "82c542", false));
                }
            }
            else if(label.equals("topico1")) {
                for(int i = 0; i < 18; i++) {
                    node.addNode(new DefaultMindmapNode("topico"  + i, "IP Number: 1.1.1." + i, "fce24f", false));
                } 
            }
            else if(label.equals("Malware")) {
                for(int i = 0; i < 18; i++) {
                    String random = UUID.randomUUID().toString();
                    node.addNode(new DefaultMindmapNode("Malware-"  + random, "Malicious Software: " + random, "3399ff", false));
                }
            }
        }   
    }
     
    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();        
    }
    
}
