package view;

import javax.swing.JOptionPane;

public abstract class CaixaDialogo {
	
	public static void exibeMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem,"Mensagem",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean perguntaSimNao(String pergunta) {
		Object[] options = { "Sim", "Não"};
		if(JOptionPane.showOptionDialog(null, pergunta, "Confirmação", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
