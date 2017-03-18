package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class TestPerceptron extends Processing{

	public int spamdoc=0,hamdoc=0;
	public void testing(String spamdirtest,String hamdirtest)
	{
		File terget_dir = new File(spamdirtest);
		File[] files = terget_dir.listFiles();		
		File hamdirect=new File(hamdirtest);
		File[] hamfiles=hamdirect.listFiles();
		Map<Integer,String> punc=new Hashtable<>();
		int spamc=0,hamc=0; 
		punc=Processing.getpunc_table();
		for (File f : files) 
		{
		//	count++;
			Map<String, Integer> dict=new Hashtable<String,Integer>();
			if(f.isFile()) 
			{
				spamdoc++;
				BufferedReader inputStream = null;
				try
				{
					inputStream = new BufferedReader(
					new FileReader(f));
					
					String line;
					while ((line = inputStream.readLine()) != null)
					{
						Scanner sc=new Scanner(line);
						String str;
						while(sc.hasNext())
						{
							str=sc.next();
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
							}
						}
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
			}
		//	temp_dict.put(f.getName(),dict);
			int result=test(dict);
			if(result==1){
				spamc++;
				}
			
			
			
		}
		
		
		//testing ham
		
		for (File f : hamfiles) 
		{
		//	count++;
			Map<String, Integer> dict=new Hashtable<String,Integer>();
			if(f.isFile()) 
			{
				hamdoc++;
				BufferedReader inputStream = null;
				try
				{
					inputStream = new BufferedReader(
					new FileReader(f));
					
					String line;
					while ((line = inputStream.readLine()) != null)
					{
						Scanner sc=new Scanner(line);
						String str;
						while(sc.hasNext())
						{
							str=sc.next();
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
							}
						}
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
			}
		//	temp_dict.put(f.getName(),dict);
			int result=test(dict);
			if(result==-1){
				hamc++;
			}
		}

		
		
		System.out.println("Spam accuracy:"+((double)spamc/(double)spamdoc)*100.0);
		System.out.println("Hamaccuracy:"+((double)hamc/(double)hamdoc)*100.0); 
		
	}
	
	
	/*public void test_ham_spam(Map<String, Map<String, Integer>> test_spam_dict, Map<String, Map<String, Integer>> test_ham_dict)
	{
		int spam_doc=0;
		for(String file:test_spam_dict.keySet() )
		{spam_doc++;
			Map<String,Integer> words_and_count=test_spam_dict.get(file);
			int result=test(words_and_count);
			if(result==1){
				spamcount++;	
			}
				
		}
		int ham_doc=0;
		for(String file:test_ham_dict.keySet() )
		{	ham_doc++;
			Map<String,Integer> words_and_count=test_ham_dict.get(file);
			int result=test(words_and_count);
			if(result==-1){
				hamcount++;	
			}
				
		}
		System.out.println("hamcount:"+hamcount);
		System.out.println("spamcount:"+spamcount);
		double hamacc=(double)hamcount/(double)ham_doc;
		double spamacc=(double)spamcount/(double)spam_doc;
		System.out.println("hamacc:"+hamacc);
		System.out.println("spamacc:"+spamacc);
		
	}
		*/
	public int test(Map<String, Integer> words_and_count) {

		double sum = bias;
		//System.out.println("bias is:"+bias);
		for(String word:words_and_count.keySet())
		{
			if(Readfile.weight_vector.get(word)!=null)
			{
				sum+=words_and_count.get(word)*Readfile.weight_vector.get(word);
			}
		}
		if(sum>0)
		{
			return 1;
		}
		else
			return -1;
	}
	
	
	public void testing_sw(String spamdirtest,String hamdirtest,String SWfile)
	{
		File terget_dir = new File(spamdirtest);
		File[] files = terget_dir.listFiles();		
		File hamdirect=new File(hamdirtest);
		File[] hamfiles=hamdirect.listFiles();
		Map<Integer,String> punc=new Hashtable<>();
		int spamcsw=0,hamcsw=0; 
		punc=Processing.getpunc_table();
		for (File f : files) 
		{
		//	count++;
			Map<String, Integer> dict=new Hashtable<String,Integer>();
			if(f.isFile()) 
			{
			//	spamdoc++;
				BufferedReader inputStream = null;
				try
				{
					inputStream = new BufferedReader(
					new FileReader(f));
					
					String line;
					while ((line = inputStream.readLine()) != null)
					{
						Scanner sc=new Scanner(line);
						String str;
						while(sc.hasNext())
						{
							str=sc.next();
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
							}
						}
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
			}
		//	temp_dict.put(f.getName(),dict);
			//removing stiop words
			
			double words=0.0;
			BufferedReader inputStream2 = null;

			try
			{
				inputStream2 = new BufferedReader(
						new FileReader(SWfile));
				String line;
				while ((line = inputStream2.readLine()) != null)
				{
					Scanner sc=new Scanner(line);
					int i=0;
					String str;
					while(sc.hasNext())
					{
						str=sc.next();
						
							if(dict.containsKey(str))
							{
								dict.remove(str);
							}
							
						}
					}
				////System.out.println("spam dict without stop words:"+spamdict.size());
			//	System.out.println("ham dict without stop words:"+hamdict.size());
			}catch(IOException e){
				e.printStackTrace();
			}
			
			//testing 
			int result=test(dict);
			if(result==1){
				spamcsw++;
				}
			
			
			
		}
		
		
		//testing ham
		
		for (File f : hamfiles) 
		{
		//	count++;
			Map<String, Integer> dict=new Hashtable<String,Integer>();
			if(f.isFile()) 
			{
				//hamdoc++;
				BufferedReader inputStream = null;
				try
				{
					inputStream = new BufferedReader(
					new FileReader(f));
					
					String line;
					while ((line = inputStream.readLine()) != null)
					{
						Scanner sc=new Scanner(line);
						String str;
						while(sc.hasNext())
						{
							str=sc.next();
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
							}
						}
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
			}
		//	temp_dict.put(f.getName(),dict);
			BufferedReader inputStream1 = null;

			try
			{
				inputStream1 = new BufferedReader(
						new FileReader(SWfile));
				String line;
				while ((line = inputStream1.readLine()) != null)
				{
					Scanner sc=new Scanner(line);
					int i=0;
					String str;
					while(sc.hasNext())
					{
						str=sc.next();
						
							if(dict.containsKey(str))
							{
								dict.remove(str);
							}
							
						}
					}
				////System.out.println("spam dict without stop words:"+spamdict.size());
			//	System.out.println("ham dict without stop words:"+hamdict.size());
			}catch(IOException e){
				e.printStackTrace();
			}
			
			
			int result=test(dict);
			if(result==-1){
				hamcsw++;
			}
			try {
				inputStream1.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
		System.out.println("Spam accuracy without stop words:"+((double)spamcsw/(double)spamdoc)*100.0);
		System.out.println("Hamaccuracy without stop words:"+((double)hamcsw/(double)hamdoc)*100.0); 
		
	}
}
