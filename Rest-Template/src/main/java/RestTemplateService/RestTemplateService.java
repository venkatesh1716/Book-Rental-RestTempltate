package RestTemplateService;
import entity.Book;
import entity.Borrower;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RestTemplateService {

    Scanner scanner = new Scanner(System.in);
    private RestTemplate restTemplate;
    public Shell shell= new Shell();

    public RestTemplateService() {
        this.restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(converter);
    }


    String uri1 = "http://localhost:8888/borrowers/";
    String uri = "http://localhost:8888/books/";

    public Book getBooksById(String id) {
            ResponseEntity<Book> responseEntity = restTemplate.exchange(uri+id, HttpMethod.GET,
                    null, new ParameterizedTypeReference<Book>(){});
            System.out.println("status code  " + responseEntity.getStatusCode());
            System.out.println(" body is " + responseEntity.getBody());
            System.out.println(" header " + responseEntity.getHeaders());
            return responseEntity.getBody();
    }

    public List<Book> getAllBooks() {
        ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Book>>() {});
        System.out.println(" status code " + responseEntity.getStatusCode());
        System.out.println(" body is " + responseEntity.getBody());
        System.out.println(" header " + responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    public void addBook(Book book) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> media = new ArrayList<>();
        media.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(media);
        HttpEntity<Book> request = new HttpEntity<>(book, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        try{
        ResponseEntity<Book> response = restTemplate.exchange(uri, HttpMethod.POST,request,Book.class);
        System.out.println("status code  " + response.getStatusCode());
        System.out.println("header " + response.getHeaders());
    }  catch (HttpStatusCodeException http){
            System.out.println("status code " + http.getStatusCode());
            System.out.println("body is " + http.getResponseBodyAsString());
            System.out.println("header " + http.getResponseHeaders());
        }

        }

    public String updateBook() {
            System.out.println("enter id");
            int id=scanner.nextInt();
            String body="{\n" +
                    "        \"authorname\": \"aravind\",\n" +
                    "        \"title\": \"ra\",\n" +
                    "        \"isbn\": 5452,\n" +
                    "        \"academic\": \"academic\",\n" +
                    "        \"totalcount\": 5\n" +
                    "}";
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity= new HttpEntity<>(body,headers);
            return  restTemplate.exchange(uri+id, HttpMethod.PUT,
                    entity,String.class).getBody();
        }

    public void deleteBook(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Book> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri+id,
                HttpMethod.DELETE, entity, String.class);
        System.out.println("status code  " + response.getStatusCode());
        System.out.println("body is "+response.getBody());
        System.out.println("header "+response.getHeaders());
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Book deleted successfully");
        }
    }

    public List<Borrower> getAllBorrowers() {
            ResponseEntity<List<Borrower>> responseEntity = restTemplate.exchange(uri1, HttpMethod.GET, null, new ParameterizedTypeReference<List<Borrower>>() {});
            System.out.println(" status code " + responseEntity.getStatusCode());
            System.out.println(" body is " + responseEntity.getBody());
            System.out.println(" header " + responseEntity.getHeaders());
            return responseEntity.getBody();
        }

    public void getBorrowerById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        System.out.println("Enter the borrower id");
        int id = scanner.nextInt();
        try {
            ResponseEntity<String> entity = restTemplate.getForEntity(uri1 + id, String.class);
            System.out.println("status code is " + entity.getStatusCode());
            System.out.println("response body is " + entity.getBody());
            System.out.println("response header " + entity.getHeaders());
        }catch(HttpStatusCodeException exception) {
                System.out.println("status code is " + exception.getStatusCode());
                System.out.println("response body is " + exception.getResponseBodyAsString());
                System.out.println("response header " + exception.getResponseHeaders());
        }
        /*System.out.println("status code is " + entity.getStatusCode());
        System.out.println("response body is " + entity.getHeaders());
        System.out.println("response header " + entity.getHeaders());*/
    }

    public void RentBook(Borrower borrower,String id) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> media = new ArrayList<>();
        media.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(media);
        HttpEntity<Borrower> request = new HttpEntity<>(borrower, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Borrower> response = restTemplate.exchange(uri1, HttpMethod.POST, request, Borrower.class);
            System.out.println("status code is " + response.getStatusCode());
            System.out.println("response body is " + response.getBody());
            System.out.println("response header " + response.getHeaders());
        }catch (HttpStatusCodeException ex){
            MessageBox msg1 = new MessageBox(shell, SWT.OK | SWT.ICON_WORKING);
            msg1.setMessage("Book insertion failed");
            msg1.open();
            System.out.println("status code " + ex.getStatusCode());
            System.out.println("body is " + ex.getResponseBodyAsString());
            System.out.println("header " + ex.getResponseHeaders());
        }
    }
    public String updateBorrower(){
        System.out.println("enter Borrower to update id");
        int id=scanner.nextInt();
        String body="{\n" +
                "        \"firstname\": \"James\",\n" +
                "         \"lastname\": \"Gausling\"\n" +
                "\t\n" +
                "}";
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity= new HttpEntity<>(body,headers);
        return  restTemplate.exchange(uri1+id, HttpMethod.PUT,
                entity,String.class).getBody();
    }
       public void deleteBorrower(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Book> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri1+id,
                HttpMethod.DELETE, entity, String.class);
        System.out.println("status code is " + response.getStatusCode());
        System.out.println("response header "+response.getHeaders());
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Borrower deleted successfully");
        }
    }


}

