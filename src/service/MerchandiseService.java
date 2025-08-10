package service;

import dao.MerchandiseDAO;
import models.Merchandise;

import java.math.BigDecimal;
import java.util.List;

/**
 * Business logic for merchandise.
 */
public class MerchandiseService {
    private final MerchandiseDAO merchandiseDAO = new MerchandiseDAO();

    /**
     * Adds a merchandise item.
     *
     * @param item Merchandise to add.
     * @return true if added, false otherwise.
     */
    public boolean add(Merchandise item) {
        return merchandiseDAO.addMerchandise(item);
    }

    /**
     * Lists all merchandise items.
     *
     * @return List of items.
     */
    public List<Merchandise> listAll() {
        return merchandiseDAO.getAllMerchandise();
    }

    /**
     * Deletes a merchandise item by id.
     *
     * @param itemId Item id.
     * @return true if deleted, false otherwise.
     */
    public boolean delete(int itemId) {
        return merchandiseDAO.deleteMerchandiseById(itemId);
    }

    /**
     * Computes total stock value.
     *
     * @return Sum of price times quantity or null on error.
     */
    public BigDecimal totalValue() {
        return merchandiseDAO.getTotalMerchValue();
    }
}