use tiendaabarrotes;
CREATE TABLE carrito (
  `idCarrito` INT NOT NULL AUTO_INCREMENT,
  `idProducto` INT NULL,
  `cantidad` INT NULL,
  PRIMARY KEY (`idCarrito`),
  INDEX `idProducto_idx` (`idProducto` ASC),
  CONSTRAINT `idProducto`
    FOREIGN KEY (`idProducto`)
    REFERENCES `tiendaabarrotes`.`producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
