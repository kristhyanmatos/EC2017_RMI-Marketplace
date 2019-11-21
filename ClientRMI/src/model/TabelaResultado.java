/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Produto;

/**
 *
 * @author kristhyanmatos
 */
public class TabelaResultado extends AbstractTableModel {
    List<Produto> lista;
    private String[] colunas = {"Nome","Preco","Loja"};
    
    
    public TabelaResultado (List<Produto> l){
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
        Produto p = lista.get(rowIndex);
        switch(ColumnIndex){
            case 0:
                p.setNome(avalue.toString());
                break;
            case 1:
                p.setPrice(Double.parseDouble(avalue.toString()));
                break;
            case 2:
                p.setLoja(avalue.toString());
            default:
                System.err.println("Indice de coluna invalido");
                break;
        }
        fireTableCellUpdated(rowIndex, ColumnIndex);
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Produto Selecionada = lista.get(rowIndex);
       Object objeto = null;
       switch(columnIndex){
           case 0:
               objeto = Selecionada.getNome();
               break;
           case 1:
               objeto = Selecionada.getPrice();
               break;
           case 2:
               objeto = Selecionada.getLoja();
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
    
    public void AtulizarTabela(List<Produto> novaLista){
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
