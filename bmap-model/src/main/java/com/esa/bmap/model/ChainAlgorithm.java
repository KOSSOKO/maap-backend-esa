package com.esa.bmap.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ChainAlgorithm
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-30T17:56:10.766+02:00")

public class ChainAlgorithm   {
  @JsonProperty("ChainAlgorithmId")
  private Long chainAlgorithmId = null;

  @JsonProperty("algoChain")
  @Valid
  private List<Algorithm> algoChain = null;

  @JsonProperty("chainName")
  private String chainName = null;

  public ChainAlgorithm chainAlgorithmId(Long chainAlgorithmId) {
    this.chainAlgorithmId = chainAlgorithmId;
    return this;
  }

  /**
   * Unique identifier representing a specific chain.
   * @return chainAlgorithmId
  **/
  @ApiModelProperty(value = "Unique identifier representing a specific chain.")


  public Long getChainAlgorithmId() {
    return chainAlgorithmId;
  }

  public void setChainAlgorithmId(Long chainAlgorithmId) {
    this.chainAlgorithmId = chainAlgorithmId;
  }

  public ChainAlgorithm algoChain(List<Algorithm> algoChain) {
    this.algoChain = algoChain;
    return this;
  }

  public ChainAlgorithm addAlgoChainItem(Algorithm algoChainItem) {
    if (this.algoChain == null) {
      this.algoChain = new ArrayList<>();
    }
    this.algoChain.add(algoChainItem);
    return this;
  }

  /**
   * Get algoChain
   * @return algoChain
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Algorithm> getAlgoChain() {
    return algoChain;
  }

  public void setAlgoChain(List<Algorithm> algoChain) {
    this.algoChain = algoChain;
  }

  public ChainAlgorithm chainName(String chainName) {
    this.chainName = chainName;
    return this;
  }

  /**
   * name of the chain.
   * @return chainName
  **/
  @ApiModelProperty(value = "name of the chain.")


  public String getChainName() {
    return chainName;
  }

  public void setChainName(String chainName) {
    this.chainName = chainName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChainAlgorithm chainAlgorithm = (ChainAlgorithm) o;
    return Objects.equals(this.chainAlgorithmId, chainAlgorithm.chainAlgorithmId) &&
        Objects.equals(this.algoChain, chainAlgorithm.algoChain) &&
        Objects.equals(this.chainName, chainAlgorithm.chainName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chainAlgorithmId, algoChain, chainName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChainAlgorithm {\n");
    
    sb.append("    chainAlgorithmId: ").append(toIndentedString(chainAlgorithmId)).append("\n");
    sb.append("    algoChain: ").append(toIndentedString(algoChain)).append("\n");
    sb.append("    chainName: ").append(toIndentedString(chainName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

