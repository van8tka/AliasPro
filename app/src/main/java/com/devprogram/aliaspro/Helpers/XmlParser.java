package com.devprogram.aliaspro.Helpers;
import android.content.Context;
import android.content.res.AssetManager;
import com.crashlytics.android.Crashlytics;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser implements IXmlParser {

    private Context context;
    private AssetManager managerAsset;
    private int version;

    public XmlParser( Context context ){
        this.context = context;
        managerAsset = context.getAssets();
    }

    @Override
    public List<String> Parse(String fileName) {
        try {
            XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = fac.newPullParser();

            String pathToFolder = getFullPath( managerAsset.list(""));;
            List<String> list = new ArrayList<String>();
            String[] listFiles = managerAsset.list(pathToFolder);
            for(int i=0;i<listFiles.length;i++)
            {
                if(fileName.equalsIgnoreCase(listFiles[i]))
                {
                    String line;
                    InputStream inputStream = managerAsset.open(pathToFolder+"/"+fileName);
                    DocumentBuilderFactory documentBuilderFactory =  DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = documentBuilderFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(inputStream);
                    Element element = doc.getDocumentElement();
                    element.normalize();
                    NodeList ndList = doc.getElementsByTagName("Item");
                    for(int j=0;j<ndList.getLength();j++)
                    {
                        Node nd = ndList.item(j);
                        if(nd.getNodeType()==Node.ELEMENT_NODE)
                        {
                            Element elItem = (Element)nd;
                            String val = elItem.getTextContent();
                            list.add(val);
                        }
                    }
                }
            }
            return list;
        } catch (Exception e) {
            Crashlytics.logException(e);
            return new ArrayList<String>();
        }
    }

    private String getFullPath(String[] list) {
        try
        {
            String pathFolder=null;
            for(int i=0;i<list.length;i++)
            {
                if(list[i].startsWith(Constants.DATA_FOLDER))
                {
                    if(pathFolder==null)
                    {
                        pathFolder = list[i];
                    }
                    else
                    {
                        int indexData = Integer.valueOf( list[i].split("V")[1] );
                        int indexPathFolder = Integer.valueOf( pathFolder.split("V")[1] );
                        if(indexData>indexPathFolder)
                            pathFolder=list[i];
                    }
                    return pathFolder;
                }
            }
            return Constants.DATA_FOLDER+1;
        }
        catch(Exception e)
        {
            Crashlytics.logException(e);
            return Constants.DATA_FOLDER+1;
        }
    }
}
