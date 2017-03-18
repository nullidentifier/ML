package Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

import com.sun.xml.internal.ws.handler.HandlerChainsModel;


public class Readfile {
	
		public static Map<String,Double> weight_vector=new HashMap<>();
		
		public static Map<String,Integer> test_spam_vocab=new HashMap<>();
		public static Map<String,Integer> test_ham_vocab=new HashMap<>();
		public static Map<String,Map<String,Integer>> test_ham_dict=new HashMap<>();
		public static Map<String,Map<String,Integer>> test_spam_dict=new HashMap<>();
		public static Map<String,Map<String,Integer>> ham_dict=new HashMap<>();
		public static Map<String,Map<String,Integer>> spam_dict=new HashMap<>();
		public static Map<String,Integer> spam_vocab=new HashMap<>();
		public static Map<String,Integer> ham_vocab=new HashMap<>();
		public static Map<String,Integer> global_vocab=new HashMap<>();
		static HashMap<String, HashSet<String>> st = new HashMap<String, HashSet<String>>();
		public int s=0,h=0;
		public  void createdict(String spamdir,String hamdir)
		{
		
			File spamfiles = new File(spamdir);			
			File[] files = spamfiles.listFiles();
			File hamfiles = new File(hamdir);			
			File[] ham_files = hamfiles.listFiles();
			
			Map<Integer,String> punc=new Hashtable<>();
			punc=Processing.getpunc_table();
			for (File f : files) 
			{
				//count++;
				Map<String, Integer> dict=new Hashtable<String,Integer>();
				if(f.isFile()) 
				{
					s++;
					BufferedReader inputStream = null;
					try
					{
						inputStream = new BufferedReader(
						new FileReader(f));
						//System.out.println("filename:"+f.getName());
						String line;
						//String[] value;
						while ((line = inputStream.readLine()) != null)
						{
							//value=line.split("\\s");
							Scanner sc=new Scanner(line);
							String str;
							while(sc.hasNext())
							{
								str=sc.next();
							//	System.out.println("str:"+str);
								if(!punc.containsValue(str))
								{
									
									if(!dict.containsKey(str))
									{
										dict.put(str, 1);
								
									}
									else
									{
										dict.put(str,dict.get(str)+1);
										
									}
									if(!spam_vocab.containsKey(str))
									{
										spam_vocab.put(str, 1);
									}
									else
									{
										spam_vocab.put(str, spam_vocab.get(str)+1);

									}
										
								}
							}
						}
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					
				}
				spam_dict.put(f.getName(),dict);
			}
			
			
			
			
			//hamfiles
			for (File f : ham_files) 
			{
				Map<String, Integer> dict=new Hashtable<String,Integer>();
				
				if(f.isFile()) 
				{
					h++;
					BufferedReader inputStream = null;
					try
					{
						inputStream = new BufferedReader(
						new FileReader(f));
						//System.out.println("filename:"+f.getName());
						String line;
						//String[] value;
						while ((line = inputStream.readLine()) != null)
						{
							//value=line.split("\\s");
							Scanner sc=new Scanner(line);
							String str;
							while(sc.hasNext())
							{
								str=sc.next();
							//	System.out.println("str:"+str);
								if(!punc.containsValue(str))
								{
									
									if(!dict.containsKey(str))
									{
										dict.put(str, 1);
								
									}
									else
									{
										dict.put(str,dict.get(str)+1);
								
										
									}
									if(!ham_vocab.containsKey(str))
									{
										ham_vocab.put(str, 1);
										
										
									}
									else
									{
										ham_vocab.put(str, ham_vocab.get(str)+1);
									}
								}
							}
						}
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					
				}
				ham_dict.put(f.getName(),dict);
			}
			
		//	System.out.println("h count:"+h+" "+"s count:"+s );
			//System.out.println(spam_dict);
		}

	
	

}
