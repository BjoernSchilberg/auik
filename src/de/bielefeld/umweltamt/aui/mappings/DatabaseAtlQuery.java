/**
 * Copyright 2005-2042, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */

package de.bielefeld.umweltamt.aui.mappings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParametergruppen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlStatus;
import de.bielefeld.umweltamt.aui.utils.GermanDouble;
import de.bielefeld.umweltamt.aui.utils.JRMapDataSource;

/**
 * This is a service class for all custom queries from the atl package.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseAtlQuery extends DatabaseBasisQuery {

    /* ********************************************************************** */
    /* Queries for package ATL                                                */
    /* ********************************************************************** */

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlAnalyseposition                     */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * This is used to load the AtlAnalysepositions for the set within the
     * Probenahme. I am not sure if they will be auto-loaded with the new
     * generated classes. TODO: Test that!
     * @param probe
     * @return Set&lt;AtlAnalyseposition&gt;
     */
    public static Set<AtlAnalyseposition> getAnalysepositionen(
        AtlProbenahmen probe) {
        Set<AtlAnalyseposition> resultSet = new HashSet<AtlAnalyseposition>();
        List<AtlAnalyseposition> resultList = new DatabaseAccess()
            .executeCriteriaToList(
                DetachedCriteria.forClass(AtlAnalyseposition.class)
                    .add(Restrictions.eq("atlProbenahmen", probe)),
                new AtlAnalyseposition());
        resultSet.addAll(resultList);
        return resultSet;
    }

    /**
     * Liefert eine Liste der Analyseinstitute.
     * @return String[]
     */
    public static String[] getAnalysierer() {
        return new DatabaseAccess().executeCriteriaToArray(
            DetachedCriteria.forClass(AtlAnalyseposition.class)
                .setProjection(Projections.distinct(
                    Projections.property("analyseVon"))),
            new String[0]);
    }

    /**
     * Liefert eine Analyseposition mit einem gegebenen Parameter
     * aus einer gegebenen Probenahme.
     * @param probe Die Probenahme
     * @param param Der Parameter
     * @return AtlAnalyseposition
     */
    public static AtlAnalyseposition getAnalyseposition(
            AtlProbenahmen probe, AtlParameter param) {
        Set<AtlAnalyseposition> set = probe.loadAtlAnalysepositionen();
        for (AtlAnalyseposition pos : set) {
            if (pos.getAtlParameter().getOrdnungsbegriff().equals(
                    param.getOrdnungsbegriff())) {
                return pos;
            }
        }
        return null;
    }

    /**
     * Get all <code>AtlAnalyseposition</code>s for a given
     * <code>AtlProbepkt</code> and <code>AtlParameter</code> in a given
     * time interval
     * @param param AtlParameter
     * @param pkt AtlProbepkt
     * @param beginDate
     * @param endDate
     * @return List&lt;AtlAnalyseposition&gt;
     */
    public static List<AtlAnalyseposition> getAnalysepositionen(
            AtlParameter param, AtlProbepkt pkt, Date beginDate, Date endDate) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlAnalyseposition.class)
                .createAlias("atlProbenahmen", "probe")
                .add(Restrictions.eq("probe.atlProbepkt", pkt))
                .add(Restrictions.eq("atlParameter", param))
                .add(Restrictions.between(
                    "probe.datumDerEntnahme", beginDate, endDate))
                .addOrder(Order.asc("probe.datumDerEntnahme")),
            new AtlAnalyseposition());
    }

    /**
     * Get all <code>AtlAnalyseposition</code>s for a given
     * <code>AtlProbenahmen</code> and sort them
     * @param probe AtlProbenahmen
     * @return List&lt;AtlAnalyseposition&gt;
     */
    public static List<AtlAnalyseposition> getSortedAnalysepositionen(
        AtlProbenahmen probe) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlAnalyseposition.class)
                .createAlias("atlParameter", "parameter")
                .add(Restrictions.eq("atlProbenahmen", probe))
                .addOrder(Order.asc("parameter.reihenfolge")),
            new AtlAnalyseposition());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlEinheiten                           */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static AtlEinheiten[] atlEinheiten = null;
    /**
     * Liefert alle in der Einheiten-Tabelle gespeicherten Einheiten.
     * @return Ein Array mit allen Einheiten
     */
    public static AtlEinheiten[] getAtlEinheiten() {
        if (DatabaseAtlQuery.atlEinheiten == null) {
            DatabaseAtlQuery.atlEinheiten =
                DatabaseQuery.getOrderedAll(new AtlEinheiten(), "bezeichnung")
                    .toArray(new AtlEinheiten[0]);
        }
        return DatabaseAtlQuery.atlEinheiten;
    }

    /**
     * Get an unit by its description
     * @param description The description of the unit (e.g. "mg/l")
     * @return <code>AtlEinheiten</code>, if an unit was found,
     *         <code>null</code> otherwise
     */
    public static AtlEinheiten getEinheitByDescription(String description) {
        return new DatabaseAccess().executeCriteriaToUniqueResult(
            DetachedCriteria.forClass(AtlEinheiten.class)
                .add(Restrictions.eq("bezeichnung", description)),
            new AtlEinheiten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlKlaeranlagen                        */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static AtlKlaeranlagen[] atlKlaeranlagen = null;
    /**
     * Get an array of all AtlKlaeranlagen,
     * omit id 7 because this is an intended duplicate
     * @return AtlKlaeranlagen[]
     */
    public static AtlKlaeranlagen[] getKlaeranlagen() {
        if (DatabaseAtlQuery.atlKlaeranlagen == null) {
            DatabaseAtlQuery.atlKlaeranlagen =
                new DatabaseAccess().executeCriteriaToArray(
                    DetachedCriteria.forClass(AtlKlaeranlagen.class)
                        .add(Restrictions.ne("id", 7))
                        .addOrder(Order.asc("id")),
                    new AtlKlaeranlagen[0]);
        }
        return DatabaseAtlQuery.atlKlaeranlagen;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlParameter                           */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert alle Parameter, die für Klärschlamm-Probenahmen relevant sind.
     * D.h. alle, deren Klärschlamm-Grenzwert nicht <code>NULL</code> ist.
     * @return Ein Array mit allen für Klärschlamm-Probenahmen relevanten
     * Parametern
     */
    public static AtlParameter[] getKlaerschlammParameter() {
        return new DatabaseAccess().executeCriteriaToArray(
            DetachedCriteria.forClass(AtlParameter.class)
                .add(Restrictions.isNotNull("klaerschlammGw"))
                .addOrder(Order.asc("bezeichnung")),
            new AtlParameter[0]);
    }

    /**
     * Liefert alle Parameter, die für SielhautBearbeiten-Probenahmen relevant
     * sind. D.h. alle, deren SielhautBearbeiten-Grenzwert nicht
     * <code>NULL</code> ist.
     *
     * @return Ein Array mit allen für SielhautBearbeiten-Probenahmen relevanten
     * Parametern
     */
    public static List<AtlParameter> getGroupedParameterAsList() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlParameter.class)
                .add(Restrictions.isNotNull("atlParametergruppen"))
                .addOrder(Order.asc("reihenfolge")),
            new AtlParameter());
    }

    /**
     * AtlParameter.getGroupedParameterAsList als Array
     *
     * @return Ein Array mit allen für Probenahmen relevanten Parametern
     */
    public static AtlParameter[] getGroupedParameter() {
        return new DatabaseAccess().executeCriteriaToArray(
            DetachedCriteria.forClass(AtlParameter.class)
                .add(Restrictions.isNotNull("atlParametergruppen"))
                .addOrder(Order.asc("reihenfolge")),
            new AtlParameter[0]);
    }

    public static List<AtlParameter> getAllParameterAsList() {
        return DatabaseQuery.getOrderedAll(new AtlParameter(), "bezeichnung");
    }

    public static AtlParameter[] getAllParameterAsArray() {
        return DatabaseQuery.getOrderedAll(new AtlParameter(), "bezeichnung")
            .toArray(new AtlParameter[0]);
    }

    public static List<AtlParameter> getParameterInGroup(int id) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlParameter.class)
                .add(Restrictions.eq("atlParametergruppen.id", id)),
            new AtlParameter());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlParametergruppen                    */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Diese Funktion pr&uuml;ft, ob die {@link AtlParameter}, die in
     * <i>group</i> enthalten sind, vollst&auml;ndig sind.
     *
     * @param id Die ID der Parametergruppe.
     * @param group Die Liste mit den Parametern.
     *
     * @return true, wenn alle Parameter der Gruppen enthalten sind, sonst
     * false.
     */
    public static boolean isCompleteParameterGroup(
        int id, List<AtlParameter> group) {
        List<AtlParameter> complete = DatabaseQuery.getParameterInGroup(id);
        // First simply check the size
        // As we use List and not Set the size is not a good criteria...
//        if (group.size() != complete.size()) {
//            return false;
//        }
        // Check if all parameters from the complete group are in the group
        if (!(group.containsAll(complete))) {
            return false;
        }
        // Be really restrictive and check if there are other parameters
//        if (!(complete.containsAll(group))) {
//            return false;
//        }
        return true;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlProbeart                            */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static AtlProbeart[] atlProbearten = null;
    /**
     * Liefert alle vorhandenen Probearten.
     * @return Alle vorhandenen Probearten
     */
    public static AtlProbeart[] getProbearten() {
        if (DatabaseAtlQuery.atlProbearten == null) {
            DatabaseAtlQuery.atlProbearten = DatabaseQuery.getOrderedAll(
                new AtlProbeart()).toArray(new AtlProbeart[0]);
        }
        return DatabaseAtlQuery.atlProbearten;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlProbenahmen                         */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert eine bestimmte Probenahme.
     * @param kennummer Die Kennummer der Probenahme
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese
     *         nicht existiert
     */
    public static AtlProbenahmen findProbenahme(String kennnummer) {
        return new DatabaseAccess().executeCriteriaToUniqueResult(
            DetachedCriteria.forClass(AtlProbenahmen.class)
                .add(Restrictions.eq("kennummer", kennnummer)),
            new AtlProbenahmen());
    }

    /**
     * Liefert alle Probenahmen einer bestimmten Art von einer bestimmten
     * Kläranlage.
     * @param art AtlProbeart
     * @param ka AtlKlaeranlagen
     * @return List&lt;AtlProbenahmen&gt;
     */
    public static List<AtlProbenahmen> findProbenahmen(
        AtlProbeart art, AtlKlaeranlagen ka) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlProbenahmen.class)
                .createAlias("atlProbepkt", "probepunkt")
                .add(Restrictions.eq("probepunkt.atlProbeart", art))
                .add(Restrictions.eq("probepunkt.atlKlaeranlagen", ka))
                .addOrder(Order.desc("datumDerEntnahme"))
                .addOrder(Order.desc("kennummer")),
            new AtlProbenahmen());
    }

    /**
     * Find AtlProbenahmen with <code>value</code> somewhere in
     * <code>propertyName</code> (search case insensitive).
     * @param propertyName String
     * @param value String
     * @return List&lt;AtlProbenahmen&gt;
     */
    public static List<AtlProbenahmen> findProbenahmen(
        String propertyName, String value) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlProbenahmen.class)
                .add(Restrictions.ilike(
                    propertyName, value, MatchMode.ANYWHERE))
                .addOrder(Order.desc("datumDerEntnahme"))
                .addOrder(Order.desc("kennummer")),
            new AtlProbenahmen());
    }

    /**
     * Find AtlProbenahmen with <code>status</code>
     * @param status AtlStatus
     * @return List&lt;AtlProbenahmen&gt;
     */
    public static List<AtlProbenahmen> findProbenahmen(AtlStatus status) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlProbenahmen.class)
                .add(Restrictions.eq("atlStatus", status))
                .addOrder(Order.desc("datumDerEntnahme"))
                .addOrder(Order.desc("kennummer")),
            new AtlProbenahmen());
    }

    /**
     * Check if there is an AtlProbenahmen with <code>kennnummer</code>
     * @param kennnummer String
     * @return <code>true</code>, if an AtlProbenahmen exists,
     *         <code>false</code> otherwise
     */
    public static Boolean probenahmeExists(String kennnummer) {
        return (!(new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlProbenahmen.class)
                .add(Restrictions.eq("kennummer", kennnummer)),
            new AtlProbenahmen()).isEmpty()));
    }

    /**
     * @param probe AtlProbenahmen
     * @return <code>true</code>, wenn die Probeart des Probepunktes dieser
     *         Probe Rohschlamm oder Faulschlamm ist, sonst <code>false</code>
     */
    public static Boolean isKlaerschlammProbe(AtlProbenahmen probe) {
        return (
            probe.getAtlProbepkt().getAtlProbeart().equals(
                DatabaseConstants.ATL_PROBEART_FAULSCHLAMM) ||
            probe.getAtlProbepkt().getAtlProbeart().equals(
                DatabaseConstants.ATL_PROBEART_ROHRSCHLAMM));
    }

    public static JRMapDataSource getAuftragDataSource(AtlProbenahmen probe) {
        List<AtlAnalyseposition> sorted = new DatabaseAccess()
            .executeCriteriaToList(
                DetachedCriteria.forClass(AtlAnalyseposition.class)
                    .createAlias("atlParameter", "parameter")
                    .add(Restrictions.eq("atlProbenahmen", probe))
                    .add(Restrictions.not(
                        Restrictions.ilike("parameter.bezeichnung",
                            "bei Probenahme", MatchMode.ANYWHERE)))
                    .addOrder(Order.asc("parameter.reihenfolge")),
                new AtlAnalyseposition());

        int elements = sorted.size();

        Object[][] values = new Object[elements][];
        Object[] columns;
        AtlParameter parameter = null;

        String[] columnsAuftrag = {"auswahl", "Parameter",
            "Kennzeichnung", "Konservierung", "Zusatz"};

        for (int i = 0; i < elements; i++) {
            columns = new Object[5];

            parameter = sorted.get(i).getAtlParameter();

            columns[0] = true; // this value is always true
            columns[1] = parameter.getBezeichnung();
            columns[2] = parameter.getKennzeichnung();
            columns[3] = parameter.getKonservierung();
            columns[4] = parameter.getAnalyseverfahren();

            values[i] = columns;
        }

        return new JRMapDataSource(columnsAuftrag, values);
    }

    public static JRMapDataSource getBescheidDataSource(AtlProbenahmen probe) {
        List<AtlAnalyseposition> sorted =
            DatabaseQuery.getSortedAnalysepositionen(probe);
        List<AtlParameter> params = new ArrayList<AtlParameter>();
        for (AtlAnalyseposition pos : sorted) {
            params.add(pos.getAtlParameter());
        }

        int elements = sorted.size();
        String[] columnsBescheid = {"Pos", "Parameter",
            "Grenzwert_Wert", "Grenzwert_Einheit", "Ergebnis_Wert",
            "Ergebnis_Einheit", "Gebühr", "Gr_Kl", "Fett", "inGruppe"};

        Object[][] values = new Object[elements][];
        Object[] columns;
        AtlAnalyseposition pos = null;
        AtlParameter parameter = null;

        for (int i = 0; i < elements; i++) {
            columns = new Object[columnsBescheid.length];
            pos = sorted.get(i);

            parameter = pos.getAtlParameter();
            String grenzwert = "";
            if (parameter.getGrenzwert() != null
                && parameter.getGrenzwert() <= 10) {
                grenzwert = parameter.getGrenzwert().toString()
                    .replace(".", ",");
            } else if (parameter.getGrenzwert() != null
                && parameter.getGrenzwert() > 10) {
                grenzwert = parameter.getGrenzwert().toString()
                    .replace(".0", "");
            }
            String wert = "";
            if (pos.getWert() == 0.0) {
                wert = pos.getWert().toString().replace(".", ",");
            } else if (pos.getWert() < 0.009) {
                wert = pos.getWert().toString().substring(0, 5);
                wert = wert.replace(".", ",");
            } else if (pos.getWert() < 100) {
                wert = pos.getWert().toString().replace(".", ",");
            } else {
                wert = pos.getWert().toString().replace(".0", "");
            }
            Boolean fett = false;
            if (pos.getWert() != null && parameter.getGrenzwert() != null
                && pos.getWert() > parameter.getGrenzwert()) {
                fett = true;
            }
            String gebuehr = "0,00 €";
            if (parameter.getPreisfueranalyse() != 0)
                gebuehr = new GermanDouble(parameter.getPreisfueranalyse())
                    .toString() + " €";

            AtlParametergruppen gr = parameter.getAtlParametergruppen();
            int groupId = gr != null ? gr.getId() : -1;

            boolean inGroup = DatabaseQuery.isCompleteParameterGroup(
                groupId, params);

//            if (inGroup) {
//                groups.put(groupId, gr);
//            }
            String einh = "";
            if (!pos.getAtlEinheiten().getBezeichnung().equals("ohne")) {
                einh = pos.getAtlEinheiten().getBezeichnung();
            }

            columns[0] = i + 1;
            columns[1] = parameter.getBezeichnung();
            columns[2] = grenzwert;
            columns[3] = einh;
            columns[4] = wert;
            columns[5] = einh;
            columns[6] = inGroup ? "0,00 €" : gebuehr;
            columns[7] = pos.getGrkl();
            columns[8] = fett;

            values[i] = columns;
        }

        Map<Integer, AtlParametergruppen> groups =
            new HashMap<Integer, AtlParametergruppen>(1);

        int numGroups = groups.size();

        if (numGroups == 0) {
            return new JRMapDataSource(columnsBescheid, values);
        }

        Object[][] newValues = new Object[elements + numGroups][];
        int i;

        for (i = 0; i < elements; i++) {
            newValues[i] = values[i];
        }

        Collection<AtlParametergruppen> theGroups = groups.values();
        for (AtlParametergruppen apg : theGroups) {

            String gebuehr = new GermanDouble(apg.getPreisfueranalyse())
                .toString() + " €";

            columns = new Object[columnsBescheid.length];
            columns[0] = i + 1;
            columns[1] = apg.getName();
            columns[2] = "";
            columns[3] = "";
            columns[4] = "";
            columns[5] = "";
            columns[6] = gebuehr;
            columns[7] = "";
            columns[8] = "";

            newValues[i] = columns;
            i++;
        }

        return new JRMapDataSource(columnsBescheid, newValues);
    }
}
