/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depedencias;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Extrato;

/**
 *
 * @author krist
 */
public class TabelaExtrato extends AbstractTableModel{
    List<Extrato> lista;
    private String[] colunas = {"Nome","Data","Preço"};
    
    
    public TabelaExtrato(List<Extrato> l){
        this.lista = l;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    @Override
    public String getColumnName(int ColumnIndex){
        return colunas[ColumnIndex];
    }
    @Override
    public Class<?> getColumnClass(int ColumnIndex){
        return String.class;
    }
    @Override
    public void setValueAt(Object avalue, int rowIndex, int ColumnIndex ){
        Extrato e = lista.get(rowIndex);
        switch(ColumnIndex){
            case 0:
                e.setNome(avalue.toString());
                break;
            case 1:
                e.setData(avalue.toString());
                break;
            case 2:
                e.setPrice(Double.parseDouble(avalue.toString()));
                break;
            default:
                System.err.println("Indice de coluna invalido");
                break;
        }
        fireTableCellUpdated(rowIndex, ColumnIndex);
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Extrato Selecionada = lista.get(rowIndex);
       Object objeto = null;
       switch(columnIndex){
           case 0:
               objeto = Selecionada.getNome();
               break;
           case 1:
               objeto = Selecionada.getData();
               break;
           case 2:
               objeto = Selecionada.getPrice();
               break;
           default:
               break;
       }
       return objeto;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int ColumnIndex){
        return false;
    }
    
    public Object getSelecionado(int rowIndex){
        return lista.get(rowIndex);
    }
    
    public void AtulizarTabela(List<Extrato> novaLista){
        this.lista = novaLista;
        fireTableDataChanged();
    }
    public void limpar(){
        lista.clear();
        fireTableDataChanged();
    }
    public boolean SeVaio(){
        return lista.isEmpty();
    }
}
