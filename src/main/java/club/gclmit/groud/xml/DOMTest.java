package club.gclmit.groud.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * <p>
 * 通过 DOM 解析  XML
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/17 12:36
 * @version: V1.0
 * @since 1.8
 */
public class DOMTest {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        /**
         *  创建 DocumentBuilderFactory 工厂对象
         *  有工厂创建 DocumentBuilder 对象
         */
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        /**
         *  documentBuilder 对象读取 XML 文档
         */
        String path = new StringBuilder(System.getProperty("user.dir")).append("\\src\\main\\resources\\").append("books.xml").toString();

        System.out.println("XML 文件路径:" + path);
        Document document = documentBuilder.parse(path);

        /**
         *
         */
        NodeList bookList = document.getElementsByTagName("book");

        System.out.println("一共有"+ bookList.getLength() + "本书");

        for (int i = 0; i < bookList.getLength() ; i++) {
            System.out.println("===============================开始遍历第"+ (i+1)+ "本书===============================");
            /**
             *  获取当前 Node 节点，并读取当前节点所有参数
             */
            Node book = bookList.item(i);
            NamedNodeMap attributes = book.getAttributes();

            System.out.println("当前dom 节点个数：" + attributes.getLength());

            /**
             *  如果在不知道 XML 文档的Node 节点名称的时候可以这样做
             */
            for (int j = 0; j < attributes.getLength(); j++) {

                Node node = attributes.item(j);
                System.out.print("node 名字："+node.getNodeName());
                System.out.print("\t类型："+node.getNodeType());
                System.out.println("\t属性值："+node.getNodeName());
            }

            /**
             *  如果知道 NOde 名称建议采用如下做法
             */
            Element element = (Element) bookList.item(i);
            String id = element.getAttribute("id");
            System.out.println("id： " +id);

            /**
             *  开始遍历子节点
             */
            NodeList childNodes = book.getChildNodes();
            System.out.println("这本书有"+ childNodes.getLength() + "个子节点");

            for (int j = 0; j < childNodes.getLength() ; j++) {
                Node node = childNodes.item(j);
                if (node.getNodeType() == 1) {
                    System.out.print("node 名字："+node.getNodeName());
                    System.out.print("\t类型："+node.getNodeType());
                    System.out.println("\t属性值："+node.getNodeName());
                }
            }

            System.out.println("===============================结束遍历第"+ (i+1)+ "本书===============================");
        }
    }
}
