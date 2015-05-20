import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class URLReader {
	
	public String Book;
	public int Chapter;
	
	static public Map<Integer,String> verses = new HashMap();
	static public Integer vsNum = 0;
	
	public URL getURL() throws Exception{
		return new URL("https://www.biblegateway.com/passage/?search="+Book+"+"+Chapter);
	}
	
    public URLReader (String ibook, int ichapter) {
    	
    	Book = ibook;
    	Chapter = ichapter;
    	
	        try{
	        	URL oracle = getURL();
	            BufferedReader in = new BufferedReader(
	                    new InputStreamReader(oracle.openStream()));
	            
	
	
	        vsNum = 0;
	        Integer nextVs = 1;
	        String str1 ="<span class=\"chapternum\">";
	    	String inputLine;
	    	String scatteredVerse="";
	
	
	        
	        boolean foundFirstVerse = false;
	        //boolean chapterLoop = false;
	        boolean reachedEndOfChapter = false;
	
		        while ((inputLine = in.readLine()) != null){
		        	if (inputLine.toLowerCase().contains(str1.toLowerCase())){
		        		while(!reachedEndOfChapter){
			            	vsNum++;
		
			            	nextVs = vsNum+1;
			        		ArrayList<String> parts = new ArrayList<String>();
			        		
			        		if(!foundFirstVerse){
				        		//get first verse
					            //System.out.println("Contains");
					    		//System.out.println("First: " +inputLine);
					    		inputLine = inputLine.substring(inputLine.indexOf(str1));
					    		inputLine = inputLine.substring(inputLine.indexOf("</span>")+7);
					    		//We are now at the beginning of vs. 1
					    		
					    		//System.out.println("Beginning at vs 1: " + inputLine);
					    		scatteredVerse = inputLine.substring(0,inputLine.indexOf("class=\"versenum\">"+vsNum+1));
					    		foundFirstVerse = true;
			        		}
			        		int stop = inputLine.indexOf("class=\"versenum\">"+nextVs.toString());
			        		if (stop == -1){
			        			stop = inputLine.length();
			        			reachedEndOfChapter = true;
			        		}
			        		int start;
			        		if (inputLine.indexOf("class=\"versenum\">"+vsNum.toString())!=-1 && 
			        				inputLine.indexOf("class=\"versenum\">"+vsNum.toString()) < stop){
			        			start = inputLine.indexOf("class=\"versenum\">"+vsNum.toString())+19;
			        		}
			        		else
			        			start = 0;
				    		scatteredVerse = inputLine.substring(start,stop);
				    		//System.out.println("scatteredVerse = " + scatteredVerse);
		
			        		boolean partsLoop = false;
			        		//breaking verse into clean parts
			        		while(!partsLoop){
			        			String newPart = scatteredVerse;// = scatteredVerse.substring(scatteredVerse.indexOf("</span>")+7);
			        			int cutOff = newPart.indexOf("<");
			        			
			        			//if(vsNum ==19)
			        			//	System.out.println("hi");
			        			
			        			//System.out.println("Cutoff value: " + cutOff);
			        			if (cutOff == -1 || stop <= cutOff){
			        				partsLoop = true;
			        				//System.out.println("Breaking");
			        			}
			        			else {
			        				//System.out.println("Adding " + newPart.substring(0, cutOff) + " to parts.");
			        				parts.add(newPart.substring(0, cutOff));
			        				scatteredVerse = scatteredVerse.substring(cutOff);
			        				//System.out.println("new scatteredVerse: " + scatteredVerse);
			        				cutOff = scatteredVerse.indexOf("</sup>");
			        				//if (cutOff == -1)
			        					//cutOff = scatteredVerse.indexOf("</span>")+1;
			        				
			        				//*******SCREWS UP IN NT**********
			        				//"LORD" is different (can't just look for </sup>)
			        				if (scatteredVerse.indexOf("Lord") != -1
			        						&& ( (scatteredVerse.indexOf("Lord") < cutOff)
			        								|| cutOff ==-1)){
			        					//cutOff = scatteredVerse.indexOf("Lord") - 6;
			        					parts.add("Lord");
			        					cutOff = scatteredVerse.indexOf("</span>")+1;
			        				}
			        				
			        				//WILL FIX FOR NT
			        				
		//	        				//Words of Jesus
		//	        				if (scatteredVerse.indexOf("woj\">“")!=-1
		//	        						&& (scatteredVerse.indexOf("woj\">“") < cutOff
		//	        								|| cutOff == -1)){
		//	        					cutOff = scatteredVerse.indexOf("woj\">“")-1;
		//	        				}
		//	        				//Caught in the gaps words surround WOJ
		//	        				if (scatteredVerse.indexOf("</span>\"")!=-1
		//	        						&& (scatteredVerse.indexOf("</span>\"") < cutOff
		//	        								|| cutOff == -1)){
		//	        					cutOff = scatteredVerse.indexOf("</span>\"")-1;
		//	        				}
		//	        				//OT QUOTES
		//	        				//***********
		//	        				
		//	        				//***********
			        				
			        				
			        				
			        				//System.out.println("cutoff" +cutOff);
			        				scatteredVerse = scatteredVerse.substring(cutOff+6);
			        			}
			        		}
			        		
			        		//put the pieces together
			        		//System.out.println("Now assembling parts..." );
			        		String newString;
			        		newString = "";
			        		for (int i = 0; i < parts.size(); i++){
			        			newString += parts.get(i);
			        		}
			        		parts.clear();
			        		//cut off extra
			        		newString = newString.substring(0, newString.indexOf("n>"));
			        		
			        		//get rid of space at beginning
			        		if (vsNum >=10){
			        			newString = newString.substring(1);
			        		}
			        			
			        		verses.put(vsNum, newString);
			        		//System.out.println("Imported vs " +vsNum);
			        		//if (reachedEndOfChapter)
			        		//	chapterLoop = true;
		
		        		}
		           	}
		
		        }
		        in.close();
	        
	        }catch(SocketTimeoutException e){
	        	System.err.println("ouch!");
	        }catch(IOException e){
	        	System.err.println("ouch!");
	        }catch(Exception e){
	        	System.err.println("ouch!");
	        }

		}
    }
