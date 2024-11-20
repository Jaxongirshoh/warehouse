package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.entity.Contractor;
import dev.wisespirit.warehouse.Exceptions.ProductNotFoundException;
import dev.wisespirit.warehouse.entity.Product;
import dev.wisespirit.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    /**
     * Returns an interable of the list of all the items in the dba
     * @return
     */
    /**
     * Return a item given its id
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Return a list of items by a given vendor id
     *
     * @param contractor
     * @return
     */
    @Override
    public List<Product> findByContractor(Contractor contractor) {
        List<Product> byContractor = productRepository.findByContractorId(contractor);
        return byContractor;
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    /**
     * Updates an existing item in the dba by a given item id
     *
     * @param id
     * @param product
     * @return
     */
    public Product updateDescriptionAndSKUById(Long id, Product product) throws ProductNotFoundException {
        if (productRepository.findById(id).isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            Product existing = productRepository.findById(id).get();

            existing.setDescription(product.getDescription());
            existing.setSku(product.getSku());
            return productRepository.save(existing);
        }
    }

    /**
     * Saves a new item in the dba
     *
     * @param item
     * @return
     */
    @Override
    public Product save(Product item) {
        return productRepository.save(item);
    }

    /**
     * Deletes a item from the dba
     *
     * @param item
     */
    @Override
    public void delete(Product item) {
        productRepository.delete(item);
    }

}
