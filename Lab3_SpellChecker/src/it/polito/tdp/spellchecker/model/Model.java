package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Model {
	
	// DIZIONARIO

	public List<String> words;
	public List<String> paroleErrate;
	
	public Model() {
		
	}
	
	public void loadDictionary(String language) {
		words = new ArrayList<String>();
		try {
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr); 
			String word;
			
			while ((word = br.readLine()) != null) {
				// Aggiungere parola alla struttura dati
				words.add(word.toLowerCase());
			}
            br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file"); }

	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList) {
		
		List<RichWord> parole = new ArrayList<RichWord>();
		RichWord tmp;
		paroleErrate = new ArrayList<String>();
		
		for(String s: inputTextList) {
			for(String w : words) {
				if(w.compareTo(s) == 0) {
					tmp = new RichWord(s,true);
				}  else {
					tmp = new RichWord(s,false);
					
					paroleErrate.add(s);
				}
				parole.add(tmp);
			}	
		}
		
		return parole;
		
	}
	
	
	
	
	public List<RichWord> spellCheckTextDichotomicaaa(List<String> inputTextList) {
		List<RichWord> parole = new ArrayList<RichWord>();
		
		paroleErrate = new ArrayList<String>();
		
		for(String s : inputTextList) {
			if(this.controlla(s,0,words.size()))
				parole.add(new RichWord(s,true));
			else {
				parole.add(new RichWord(s, false));
				paroleErrate.add(s);
			}
			
		}
		return parole;
	}
	
	private boolean controlla(String s, int inizio, int fine) {
		int meta = (inizio+fine)/2;
		if((fine-inizio)<=0)
			return false;
		if(s.compareTo(words.get(meta))==0)
			return true;
		else if(s.compareTo(words.get(meta))>0) 
			return this.controlla(s, meta+1, fine);
		else 
			return this.controlla(s, inizio, meta-1);
		
		
		
	}

	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList) {
		List<RichWord> parole = new ArrayList<RichWord>();
		
		paroleErrate = new ArrayList<String>();
		
		for(String s : inputTextList) {
			boolean trovato=false;
			int inizio = 0;
			int fine = words.size();
			while(!trovato && (fine-inizio)>0) {
				int meta=(fine+inizio)/2;
				if(s.compareTo(words.get(meta))== 0) {
					trovato=true;
					parole.add(new RichWord(s,true));
				} else if(s.compareTo(words.get(meta)) > 0) {
					inizio =meta+1;
					
				} else {
					fine = meta-1;
				}
					
				
			}
			if(!trovato) {
				parole.add(new RichWord(s,false));
				paroleErrate.add(s);
			}
				
			
		}
		return parole;
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		
		List<RichWord> parole = new ArrayList<RichWord>();
		RichWord tmp;
		paroleErrate = new ArrayList<String>();
		for(String s: inputTextList) {
			
			if(words.contains(s)) {
				tmp = new RichWord(s,true);
			} else {
				tmp = new RichWord(s,false);
				
				paroleErrate.add(s);
			}
			parole.add(tmp);
		}
		
		return parole;
	}
	
	public String getParoleErrate() {
		
		String s = "";
		
		for (String st: paroleErrate) {
			s += st+"\n";
		}
		return s.trim();
	}
	
	public String numErrate() {
		return ""+paroleErrate.size();
	}

	public void reset() {
		paroleErrate.clear();
		words.clear();
		// TODO Auto-generated method stub
		
	}
	
	
}
