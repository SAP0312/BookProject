package com.bookCRUD.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Service
@Qualifier("uploadService")
public class UploadServiceImpl implements UploadService {
    @Autowired
    private EntityManager entityManager;

    public String fileUpload(MultipartFile file){
        Session session = entityManager.unwrap(Session.class);
        Transaction tx = session.beginTransaction();
        //get Connection from Session
        session.doWork((conn)-> {
                PreparedStatement pstmt = null;
                try{
                    String sqlInsert = "insert into book (author,category,language,publisher,title) values (?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sqlInsert );
                    int i=1;
                    InputStream inputFilestream = file.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream));
                    String line = "";
                    while ((line = br.readLine()) != null) {

                        String[] bookDetails = line.split(",");
                        pstmt.setString(1,bookDetails[0]);
                        pstmt.setString(2,bookDetails[1]);
                        pstmt.setString(3,bookDetails[2]);
                        pstmt.setString(4,bookDetails[3]);
                        pstmt.setString(5,bookDetails[4]);
                        pstmt.addBatch();
                        if ( i % 1000 == 0 ) {
                            pstmt .executeBatch();
                        }
                        i++;
                    }
                    pstmt.executeBatch();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch(JDBCConnectionException t) {
                    System.out.println("================ {{{");
                    SQLException current = t.getSQLException();
                    do {
                        current.printStackTrace();
                    } while ((current = current.getNextException()) != null);
                    System.out.println("================ }}}");
                    throw t;
                }
                finally {
                    pstmt.close();
                }
            }
        );

        tx.commit();
        session.close();



        return "Uploaded Succesfully";
    }
}
