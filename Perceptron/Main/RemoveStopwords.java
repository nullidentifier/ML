package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class RemoveStopwords {
	
	
	public static Map<String,Map<String,Integer>> ham_dict_sw=new HashMap<>();
	public static Map<String,Map<String,Integer>> spam_dict_sw=new HashMap<>();
	public static Map<String,Integer> spam_vocab_sw=new HashMap<>();
	public static Map<String,Integer> ham_vocab_sw=new HashMap<>();
	int h=0,s=0;
	
	
	
		public void remove_sw(String SWfile)
		{
			//int i=0;
			double words=0.0;
			BufferedReader inputStream = null;

			try
			{
				inputStream = new BufferedReader(
						new FileReader(SWfile));
				String line;
				while ((line = inputStream.readLine()) != null)
				{
					Scanner sc=new Scanner(line);
					int i=0;
					String str;
					while(sc.hasNext())
					{
						str=sc.next();
						for(String f:Readfile.spam_dict.keySet())
						{
							Map<String,Integer> temp=Readfile.spam_dict.get(f);
							if(temp.containsKey(str))
							{
								temp.remove(str);
							}
							Readfile.spam_dict.put(f, temp);
						}	
						
							if(Readfile.spam_vocab.containsKey(str))
							{
								Readfile.spam_vocab.remove(str);
							}
								
						
						//for ham
						for(String f:Readfile.ham_dict.keySet())
						{
							Map<String,Integer> temp=Readfile.ham_dict.get(f);
							if(temp.containsKey(str))
							{
								temp.remove(str);
							}
							Readfile.ham_dict.put(f, temp);
						}	
						
							if(Readfile.ham_vocab.containsKey(str))
							{
								Readfile.ham_vocab.remove(str);
							}
								
						
						
						

						}
					}
				////System.out.println("spam dict without stop words:"+spamdict.size());
			//	System.out.println("ham dict without stop words:"+hamdict.size());
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	
	
}
