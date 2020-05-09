import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
public class GreatGatsby

{
	private static String mostCommon = "";
	private static HashMap<String, ArrayList<String>> hash = new HashMap<String,ArrayList<String>>(); 

	private static void makeProbability(String text, int order)
	{
		int maxInt = 0;
		for(int i = order; i < text.length(); i++)
		{
			String combo = text.substring(i-order, i);
			if(hash.containsKey(combo)==false)
			{
				ArrayList<String> insertion = new ArrayList<String>();
				insertion.add("" + (text.charAt(i)));
				hash.put(combo,insertion);
			}
			else
			{
				hash.get(combo).add("" +text.charAt(i));
				if(hash.get(combo).size() > maxInt) // new most common string
				{
					mostCommon = combo;
					maxInt = hash.get(combo).size();
				}
			}
		}

	}


	private static void writeText(String text, int order, int characters) throws FileNotFoundException
	{
		makeProbability(text,order);
		String txt = mostCommon;
		PrintWriter out = new PrintWriter("output.txt");
		out.print(mostCommon);
		for(int i = order; i < characters; i++)
		{
			String combo = txt.substring(txt.length() - order , txt.length());
			ArrayList<String> current = hash.get(combo);
			int randoChoix = (int) (Math.random()*(current.size()));
			out.print(current.get(randoChoix));
			txt += current.get(randoChoix);
		}
		out.close();
	}
	
	public static void main(String[] args) throws IOException 
	{
		String input = "";
		BufferedReader br=new BufferedReader(new FileReader("thegreatgatsby.txt"));
		while (br.ready()==true)
		{
			input += (char) br.read();
		}
		br.close();
		writeText(input, 8, 500);
	}


}