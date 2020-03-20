package com.esa.bmap.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

/**
 * This class represents a quadrangle.
 * 
 * @author QFAURE
 *
 */
@Entity
@Table(name = "quadrangle")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Quadrangle {

	/**
	 * Unique number identifying the bounding box.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	/**
	 * Type of bounding box (e.g. XY, LATLONG).
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = true, length = 7)
	private QuadrangleType type = null;

	/**
	 * Spatial geometry representing the bounding box spatially.
	 */
	@JsonSerialize(using = GeometrySerializer.class)
	@JsonDeserialize(contentUsing = GeometryDeserializer.class)
	@Column(name = "geometry", nullable = true, columnDefinition = "geometry(polygon, 4326)")
	private Geometry geometry = null;

	/**
	 * Width of the bounding box in pixels.
	 */
	@Column(name = "width", nullable = true)
	private Long width = null;

	/**
	 * Height of the bounding box in pixels.
	 */
	@Column(name = "height", nullable = true)
	private Long height = null;

	/**
	 * Creates an empty bounding box.
	 */
	public Quadrangle() {
		super();
	}

	/**
	 * Creates a bounding box with the specified parameters.
	 * 
	 * @param type
	 * Type of bounding box (e.g. XY, LATLONG).
	 * @param upperLeft
	 * Upper left coordinate.
	 * @param upperRight
	 * Upper right coordinate.
	 * @param lowerLeft
	 * Lower left coordinate.
	 * @param lowerRight
	 * Lower right coordinate.
	 * @param width
	 * Width or the bounding box in pixels.
	 * @param height
	 * Height or the bounding box in pixels.
	 */
	public Quadrangle(QuadrangleType type, Coordinate[] coordinates, long width, long height) {
		super();
		this.type = type;
		if (this.type.equals(QuadrangleType.LATLONG)) {
			coordinates = new Coordinate[] { coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[0] };
			GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);
			this.geometry = geometryFactory.createPolygon(coordinates);
		}
		this.width = width;
		this.height = height;
	}

	/**
	 * @return The id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 * The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return The type.
	 */
	public QuadrangleType getType() {
		return type;
	}

	/**
	 * @param type
	 * The type to set.
	 */
	public void setType(QuadrangleType type) {
		this.type = type;
	}

	/**
	 * @return The geometry.
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * @param geometry
	 * The geometry to set.
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	/**
	 * @return The width.
	 */
	public Long getWidth() {
		return width;
	}

	/**
	 * @param width
	 * The width to set.
	 */
	public void setWidth(Long width) {
		this.width = width;
	}

	/**
	 * @return The height.
	 */
	public Long getHeight() {
		return height;
	}

	/**
	 * @param height
	 * The height to set.
	 */
	public void setHeight(Long height) {
		this.height = height;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Quadrangle quadrangle = (Quadrangle) o;
		return Objects.equals(this.id, quadrangle.id) && Objects.equals(this.type, quadrangle.type) && Objects.equals(this.geometry, quadrangle.geometry) && Objects.equals(this.width, quadrangle.width) && Objects.equals(this.height, quadrangle.height);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.type, this.geometry, this.width, this.height);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Quadrangle {\n");
		sb.append("    id: ").append(Utils.toIndentedString(this.id)).append("\n");
		sb.append("    type: ").append(Utils.toIndentedString(this.type)).append("\n");
		//sb.append("    geometry: ").append(Utils.toIndentedString(this.geometry)).append("\n");
		sb.append("    width: ").append(Utils.toIndentedString(this.width)).append("\n");
		sb.append("    height: ").append(Utils.toIndentedString(this.height)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
