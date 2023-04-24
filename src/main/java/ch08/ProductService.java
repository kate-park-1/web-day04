package ch08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
  Map<String, Product> products = new HashMap<>();

  public ProductService(){
    Product p = new Product("101", "애플폰","애플", 1200000, "2023-04-24");
    products.put("101", p);
    p = new Product("102", "삼성폰201","삼성전자", 1100000, "2023-04-24");
    products.put("102", p);
    p = new Product("103", "삼성폰203","삼성전자", 1200000, "2023-04-24");
    products.put("103", p);
  }

  // 전체 상품정보를 가져와서 list에 반환해주는 메서드
  public List<Product> findAll(){
    return new ArrayList<>(products.values());
  }
  // 특정 상품정보를 가져와서 반환해주는 메서드
  public Product find(String id){
    return products.get(id);
  }
}
