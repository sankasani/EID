package app.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

class SortedListModel2 extends AbstractListModel<Object> {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
SortedSet<Object> model;

  public SortedListModel2() {
    model = new TreeSet<Object>();
  }

  public int getSize() {
    return model.size();
  }

  public Object getElementAt(int index) {
    return model.toArray()[index];
  }

  public void add(Object element) {
    if (model.add(element)) {
      fireContentsChanged(this, 0, getSize());
    }
  }

  public void addAll(Object elements[]) {
    Collection<Object> c = Arrays.asList(elements);
    model.addAll(c);
    fireContentsChanged(this, 0, getSize());
  }

  public void clear() {
    model.clear();
    fireContentsChanged(this, 0, getSize());
  }

  public boolean contains(Object element) {
    return model.contains(element);
  }

  public Object firstElement() {
    return model.first();
  }

  public Iterator<Object> iterator() {
    return model.iterator();
  }

  public Object lastElement() {
    return model.last();
  }

  public boolean removeElement(Object element) {
    boolean removed = model.remove(element);
    if (removed) {
      fireContentsChanged(this, 0, getSize());
    }
    return removed;
  }
}

public class TestSuiteSelector extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private JList<?> sourceList;

  private static SortedListModel2 sourceListModel;

  public static JList<?> destList;

  private static SortedListModel2 destListModel;

  private JButton addButton;

  private JButton removeButton;

  public TestSuiteSelector() {
    initScreen();
  }

  public static void clearSourceListModel() {
    sourceListModel.clear();
  }

  public static void clearDestinationListModel() {
    destListModel.clear();
  }

  public void addSourceElements(ListModel<?> newValue) {
    fillListModel(sourceListModel, newValue);

  }

  public void setSourceElements(ListModel<?> newValue) {
    clearSourceListModel();
    addSourceElements(newValue);

  }

  public void addDestinationElements(ListModel<?> newValue) {
    fillListModel(destListModel, newValue);

  }

  private void fillListModel(SortedListModel2 model, ListModel<?> newValues) {
    int size = newValues.getSize();
    for (int i = 0; i < size; i++) {
      model.add(newValues.getElementAt(i));
    }
  }

  public void addSourceElements(Object newValue[]) {
    fillListModel(sourceListModel, newValue);
    sourceList.setSelectedIndex(0);

  }

  public void setSourceElements(Object newValue[]) {
    clearSourceListModel();
    addSourceElements(newValue);
    sourceList.setSelectedIndex(0);

  }

  public void addDestinationElements(Object newValue[]) {
    fillListModel(destListModel, newValue);
    destList.setSelectedIndex(0);

// get Names selected
  }

  private void fillListModel(SortedListModel2 model, Object newValues[]) {
    model.addAll(newValues);
  }

  public void clearSourceSelected() {
    //Object selected[] = sourceList.getSelectedValues();
    Object selected[] = sourceList.getSelectedValuesList().toArray();

    for (int i = selected.length - 1; i >= 0; --i) {
      sourceListModel.removeElement(selected[i]);
    }
    sourceList.getSelectionModel().clearSelection();
    sourceList.setSelectedIndex(0);

  }

  public void clearDestinationSelected() {
   // Object selected[] = destList.getSelectedValues();
    Object selected[] = destList.getSelectedValuesList().toArray();

    for (int i = selected.length - 1; i >= 0; --i) {
      destListModel.removeElement(selected[i]);
      System.out.println("Removed TestCase-->" +selected[i]);
    }
    destList.getSelectionModel().clearSelection();
    destList.setSelectedIndex(0);

  }

  private void initScreen() {
    setLayout(new GridLayout(0, 2));
    sourceListModel = new SortedListModel2();
    sourceList = new JList<Object>(sourceListModel);

    addButton = new JButton(">>");
    addButton.addActionListener(new AddListener());
    removeButton = new JButton("<<");
    removeButton.addActionListener(new RemoveListener());

    destListModel = new SortedListModel2();
    destList = new JList<Object>(destListModel);
    
   

    JPanel leftPanel = new JPanel(new BorderLayout());
    leftPanel.add(new JLabel("Available Testsuites:"), BorderLayout.NORTH);
    leftPanel.add(new JScrollPane(sourceList), BorderLayout.CENTER);
    leftPanel.add(addButton, BorderLayout.SOUTH);

    JPanel rightPanel = new JPanel(new BorderLayout());

    rightPanel.add(new JLabel("Selected Testsuites:"), BorderLayout.NORTH);
    rightPanel.add(new JScrollPane(destList), BorderLayout.CENTER);
    rightPanel.add(removeButton, BorderLayout.SOUTH);

    add(leftPanel);
    add(rightPanel);
  }

  public class AddListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      //Object selected[] = sourceList.getSelectedValues();
      Object selected[] = sourceList.getSelectedValuesList().toArray();

      addDestinationElements(selected);
      for(int i=0;i<selected.length;i++) {
          System.out.println("Selected Test Case-->"+ selected[i]);
      }
      clearSourceSelected();
    }
  }

  private class RemoveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
     // Object selected[] = destList.getSelectedValues();
      Object selected[] = destList.getSelectedValuesList().toArray();

      addSourceElements(selected);
      clearDestinationSelected();
    }
  }
  
  
  
  public void clearSourceSelectedSuites(String item) {
	   // Object selected[] = sourceList.getSelectedValues();
	  //  for (int i = selected.length - 1; i >= 0; --i) {
	      sourceListModel.removeElement(item);
	//    }
	  //  sourceList.getSelectionModel().clearSelection();
	  }


  

  
  
  
}