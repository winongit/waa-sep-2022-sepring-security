package edu.miu.springsecurity.service.impl;

import edu.miu.springsecurity.entity.Product;
import edu.miu.springsecurity.repository.ProductRepo;
import edu.miu.springsecurity.security.AwesomeUserDetails;
import edu.miu.springsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;



    @Autowired
    private HttpServletRequest request;

    @Override
    public void save(Product p) {

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        AwesomeUserDetails userDetails = (AwesomeUserDetails)authenticationToken.getPrincipal();
        p.setUser(userDetails.getUser());

        productRepo.save(p);
    }

    @Override
    public void delete(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getById(int id) {
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        var result = new ArrayList<Product>();
        productRepo.findAll().forEach(result::add);
        return result;
    }
}
