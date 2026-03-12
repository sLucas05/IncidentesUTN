package g6c006.Classes;

import java.time.LocalTime;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-03-12T10:54:43", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Incidentes.class)
public class Incidentes_ { 

    public static volatile SingularAttribute<Incidentes, String> descripcion;
    public static volatile SingularAttribute<Incidentes, String> servicio;
    public static volatile SingularAttribute<Incidentes, String> resolucion;
    public static volatile SingularAttribute<Incidentes, Integer> tecnico;
    public static volatile SingularAttribute<Incidentes, String> type;
    public static volatile SingularAttribute<Incidentes, Boolean> resuelto;
    public static volatile SingularAttribute<Incidentes, Integer> cliente;
    public static volatile SingularAttribute<Incidentes, String> complejidad;
    public static volatile SingularAttribute<Incidentes, Date> solvDate;
    public static volatile SingularAttribute<Incidentes, Date> reportDate;
    public static volatile SingularAttribute<Incidentes, String> id;
    public static volatile SingularAttribute<Incidentes, LocalTime> reportTime;
    public static volatile SingularAttribute<Incidentes, LocalTime> solvTime;

}