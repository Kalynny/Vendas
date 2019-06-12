package br.com.Vendas.Relatorio;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import br.com.Vendas.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {

	@SuppressWarnings("deprecation")
	public void relVendas(int cdVendas) {
			Connection conexao = HibernateUtil.getConnection1();

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("CD_VENDA", cdVendas);
			
			String caminho = Faces.getRealPath("/reports/PedVen.jasper");

		try {
			JasperPrint print = JasperFillManager.fillReport(caminho, parametros, conexao);
		
			JasperViewer view = new JasperViewer(print, false);
			view.show();
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao Gerar relatório");
		}
		
	}
		@SuppressWarnings("deprecation")
	public void relCompras(int cdCompras) {
			Connection conexao = HibernateUtil.getConnection1();

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("CD_COMPRA", cdCompras);
			
			String caminho = Faces.getRealPath("/reports/PedCom.jasper");

		try {
			JasperPrint print = JasperFillManager.fillReport(caminho, parametros, conexao);
		
			JasperViewer view = new JasperViewer(print, false);
			view.show();
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao Gerar relatório");
		}
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void impfun() {

		try {

			String caminho = Faces.getRealPath("/reports/Funcionario.jasper");

			// Map<String, Object> paramentros = new HashMap<>();

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, null, conexao);
			JasperViewer view = new JasperViewer(relatorio, false);
			view.show();

			// JasperPrintManager.printReport(relatorio, true);

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void relOrcamento(int cdOrcamento) {
			Connection conexao = HibernateUtil.getConnection1();

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("CD_ORC", cdOrcamento);
			
			String caminho = Faces.getRealPath("/reports/Orcamento.jasper");

		try {
			JasperPrint print = JasperFillManager.fillReport(caminho, parametros, conexao);
		
			JasperViewer view = new JasperViewer(print, false);
			view.show();
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao Gerar relatório");
		}
		
	}

}