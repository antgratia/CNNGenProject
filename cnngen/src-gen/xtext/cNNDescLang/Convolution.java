/**
 * generated by Xtext 2.26.0
 */
package xtext.cNNDescLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Convolution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link xtext.cNNDescLang.Convolution#getBnconv <em>Bnconv</em>}</li>
 *   <li>{@link xtext.cNNDescLang.Convolution#getConvbn <em>Convbn</em>}</li>
 *   <li>{@link xtext.cNNDescLang.Convolution#getConv <em>Conv</em>}</li>
 *   <li>{@link xtext.cNNDescLang.Convolution#getUpconv <em>Upconv</em>}</li>
 * </ul>
 *
 * @see xtext.cNNDescLang.CNNDescLangPackage#getConvolution()
 * @model
 * @generated
 */
public interface Convolution extends EObject
{
  /**
   * Returns the value of the '<em><b>Bnconv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bnconv</em>' attribute.
   * @see #setBnconv(String)
   * @see xtext.cNNDescLang.CNNDescLangPackage#getConvolution_Bnconv()
   * @model
   * @generated
   */
  String getBnconv();

  /**
   * Sets the value of the '{@link xtext.cNNDescLang.Convolution#getBnconv <em>Bnconv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bnconv</em>' attribute.
   * @see #getBnconv()
   * @generated
   */
  void setBnconv(String value);

  /**
   * Returns the value of the '<em><b>Convbn</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Convbn</em>' attribute.
   * @see #setConvbn(String)
   * @see xtext.cNNDescLang.CNNDescLangPackage#getConvolution_Convbn()
   * @model
   * @generated
   */
  String getConvbn();

  /**
   * Sets the value of the '{@link xtext.cNNDescLang.Convolution#getConvbn <em>Convbn</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Convbn</em>' attribute.
   * @see #getConvbn()
   * @generated
   */
  void setConvbn(String value);

  /**
   * Returns the value of the '<em><b>Conv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conv</em>' attribute.
   * @see #setConv(String)
   * @see xtext.cNNDescLang.CNNDescLangPackage#getConvolution_Conv()
   * @model
   * @generated
   */
  String getConv();

  /**
   * Sets the value of the '{@link xtext.cNNDescLang.Convolution#getConv <em>Conv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Conv</em>' attribute.
   * @see #getConv()
   * @generated
   */
  void setConv(String value);

  /**
   * Returns the value of the '<em><b>Upconv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upconv</em>' attribute.
   * @see #setUpconv(String)
   * @see xtext.cNNDescLang.CNNDescLangPackage#getConvolution_Upconv()
   * @model
   * @generated
   */
  String getUpconv();

  /**
   * Sets the value of the '{@link xtext.cNNDescLang.Convolution#getUpconv <em>Upconv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upconv</em>' attribute.
   * @see #getUpconv()
   * @generated
   */
  void setUpconv(String value);

} // Convolution
