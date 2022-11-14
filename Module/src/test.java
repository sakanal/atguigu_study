//package step3;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.TableDescriptors;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.Admin;
//import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
//import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.apache.hadoop.hbase.client.Get;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.client.Result;
//import org.apache.hadoop.hbase.client.ResultScanner;
//import org.apache.hadoop.hbase.client.Scan;
//import org.apache.hadoop.hbase.client.Table;
//import org.apache.hadoop.hbase.client.TableDescriptor;
//import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
//import org.apache.hadoop.hbase.util.Bytes;
//
//public class test {
//
//    public void batchPut()throws Exception{
//        /********* Begin *********/
//        Configuration config=HBaseConfiguration.create();
//        Connection connection=ConnectionFactory.createConnection(config);
//        Admin admin=connection.getAdmin();
//        TableName tableName=TableName.valueOf("stu");
////        TableDescriptorBuilder tableDescriptor=TableDescriptorBuilder.newBuilder(tableName);
////        ColumnFamilyDescriptor family=ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("basic_info")).build();
////        tableDescriptor.setColumnFamily(family);
////        family=ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("school_info")).build();
////        tableDescriptor.setColumnFamily(family);
////        admin.createTable(tableDescriptor.build());
//
//        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
//        HColumnDescriptor columnDescriptor_1 = new HColumnDescriptor(Bytes.toBytes("basic_info"));
//        HColumnDescriptor columnDescriptor_2 = new HColumnDescriptor(Bytes.toBytes("school_info"));
//        tableDescriptor.addFamily(columnDescriptor_1);
//        tableDescriptor.addFamily(columnDescriptor_2);
//        admin.createTable(tableDescriptor);
//
//
//        List<String> basicInfo=new ArrayList<>();
//        List<String> basicInfoStu1=new ArrayList<>();
//        List<String> basicInfoStu2=new ArrayList<>();
//        basicInfo.add("name");
//        basicInfoStu1.add("阿克蒙德");
//        basicInfoStu2.add("萨格拉斯");
//        basicInfo.add("gender");
//        basicInfoStu1.add("male");
//        basicInfoStu2.add("male");
//        basicInfo.add("birthday");
//        basicInfoStu1.add("1987-05-23");
//        basicInfoStu2.add("1986-05-23");
//        basicInfo.add("connect");
//        basicInfoStu1.add("	tel:13974036666");
//        basicInfoStu2.add("tel:18774036666");
//        basicInfo.add("address");
//        basicInfoStu1.add("HuNan-ChangSha");
//        basicInfoStu2.add("HuNan-ChangSha");
//
//        List<String> schoolInfo=new ArrayList<>();
//        List<String> schoolInfoStu1=new ArrayList<>();
//        List<String> schoolInfoStu2=new ArrayList<>();
//        schoolInfo.add("college");
//        schoolInfoStu1.add("ChengXing");
//        schoolInfoStu2.add("ChengXing");
//        schoolInfo.add("class");
//        schoolInfoStu1.add("class 1 grade 2");
//        schoolInfoStu2.add("class 2 grade 2");
//        schoolInfo.add("object");
//        schoolInfoStu1.add("Software");
//        schoolInfoStu2.add("Software");
//
//        List<Put> puts = new ArrayList<>();
//
//        List<byte[]> row=new ArrayList<>();
//        row.add(Bytes.toBytes("20181122"));
//        row.add(Bytes.toBytes("20181123"));
//
//        // 循环添加数据
//        for (int i = 0; i <= 1; i++) {
//            Put put1 = new Put(row.get(i));
//            for(int j=0;j<basicInfo.size();j++){
//                put1.addColumn(Bytes.toBytes("basic_info"), Bytes.toBytes(basicInfo.get(j)), Bytes.toBytes(basicInfoStu1.get(j)));
//            }
//            for(int j=0;j<schoolInfo.size();j++){
//                put1.addColumn(Bytes.toBytes("school_info"), Bytes.toBytes(schoolInfo.get(j)), Bytes.toBytes(schoolInfoStu1.get(j)));
//            }
//            Put put2 = new Put(row.get(i+1));
//            for(int j=0;j<basicInfo.size();j++){
//                put2.addColumn(Bytes.toBytes("basic_info"), Bytes.toBytes(basicInfo.get(j)), Bytes.toBytes(basicInfoStu2.get(j)));
//            }
//            for(int j=0;j<schoolInfo.size();j++){
//                put2.addColumn(Bytes.toBytes("school_info"), Bytes.toBytes(schoolInfo.get(j)), Bytes.toBytes(schoolInfoStu2.get(j)));
//            }
//            puts.add(put1);
//            puts.add(put2);
//        }
//        Table table = connection.getTable(tableName);
//        table.put(puts);
//
//        /********* End *********/
//    }
//}