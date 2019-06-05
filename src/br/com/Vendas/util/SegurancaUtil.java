package br.com.Vendas.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.omnifaces.util.Faces;
import br.com.Vendas.Bean.AutenticacaoBean;
import br.com.Vendas.domain.Funcionario;

@SuppressWarnings("serial")
public class SegurancaUtil implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {

		String pagina = Faces.getViewId();
		boolean login = pagina.contains("login.xhtml");

		if (!login) {
			AutenticacaoBean autenticacao = Faces.getSessionAttribute("autenticacao");
			
			if(autenticacao == null) {
				Faces.navigate("/faces/pages/login.xhtml");
				return;
				
			}
			Funcionario funcionario = autenticacao.getFuncionarioLogado();
				
			
			if(pagina.contains("/pages/principal.xhtml")){
				if(funcionario.getFuncao().equals("Vendedor")){	
					
				}else{
					
					Faces.navigate("/pages/login.xhtml");
				//	Messages.addFlashGlobalError("Você não tem privilégios para entrar nesta seção!");
					return;
				}
			}
		}

	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}
}
