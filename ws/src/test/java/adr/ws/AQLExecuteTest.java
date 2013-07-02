package adr.ws;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.DADLParser;
import org.openehr.rm.binding.DADLBinding;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.support.identification.HierObjectID;

public class AQLExecuteTest extends AQLExecuteTestBase {

	public AQLExecuteTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testDelete() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			List<String> results = aqlImpl.select(query, null);

			for (String obj : results) {
				System.out.println(obj);
			}

			assertEquals(results.size(), 3);
		}

		{
			String query = "delete "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/details[at0001]/items[at0009]/value/value = 'lisi'";
			int ret = aqlImpl.delete(query);

			assertEquals(ret, 1);
		}

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			List<String> results = aqlImpl.select(query, null);

			for (String obj : results) {
				System.out.println(obj);
			}

			assertEquals(results.size(), 2);
		}

		{
			String query = "delete "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			int ret = aqlImpl.delete(query);

			assertEquals(ret, 2);
		}

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			List<String> results = aqlImpl.select(query, null);

			for (String obj : results) {
				System.out.println(obj);
			}

			assertEquals(results.size(), 0);
		}

		cleanTestBaseData();
	}

	@Test
	public void testDeleteParameterized() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			List<String> results = aqlImpl.select(query, null);

			for (String obj : results) {
				System.out.println(obj);
			}

			assertEquals(results.size(), 3);
		}

		{
			String query = "delete "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/details[at0001]/items[at0009]/value/value = :name";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("name", "lisi");
			int ret = aqlImpl.delete(query, parameters);

			assertEquals(ret, 1);
		}

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			List<String> results = aqlImpl.select(query, null);

			for (String obj : results) {
				System.out.println(obj);
			}

			assertEquals(results.size(), 2);
		}

		{
			String query = "delete "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			int ret = aqlImpl.delete(query);

			assertEquals(ret, 2);
		}

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			List<String> results = aqlImpl.select(query, null);

			for (String obj : results) {
				System.out.println(obj);
			}

			assertEquals(results.size(), 0);
		}

		cleanTestBaseData();
	}

	@Test
	public void testFrom() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();
		DADLBinding binding = new DADLBinding();

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			List<String> results = aqlImpl.select(query, "", null);

			assertEquals(results.size(), 3);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1984-08-11T19:20:30+08:00");
			assertEquals(d4, "zhangsan");

			DADLParser parser2 = new DADLParser(results.get(1));
			ContentObject contentObj2 = parser2.parse();
			Locatable loc2 = (Locatable) binding.bind(contentObj2);
			String d5 = (String) loc2.itemAtPath("/uid/value");
			String d6 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d7 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d8 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d5, "patient2");
			assertEquals(d6, "F");
			assertEquals(d7, "1986-08-11T19:20:30+08:00");
			assertEquals(d8, "lisi");

			DADLParser parser3 = new DADLParser(results.get(2));
			ContentObject contentObj3 = parser3.parse();
			Locatable loc3 = (Locatable) binding.bind(contentObj3);
			String d9 = (String) loc3.itemAtPath("/uid/value");
			String d10 = (String) loc3
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d11 = (String) loc3
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d12 = (String) loc3
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d9, "patient3");
			assertEquals(d10, "O");
			assertEquals(d11, "1988-08-11T19:20:30+08:00");
			assertEquals(d12, "wangwu");
		}

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/details[at0001]/items[at0009]/value/value = 'lisi'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 1);

			DADLParser parser2 = new DADLParser(results.get(0));
			ContentObject contentObj2 = parser2.parse();
			Locatable loc2 = (Locatable) binding.bind(contentObj2);
			String d5 = (String) loc2.itemAtPath("/uid/value");
			String d6 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d7 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d8 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d5, "patient2");
			assertEquals(d6, "F");
			assertEquals(d7, "1986-08-11T19:20:30+08:00");
			assertEquals(d8, "lisi");
		}

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/uid/value = 'patient1'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 1);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1984-08-11T19:20:30+08:00");
			assertEquals(d4, "zhangsan");
		}

		cleanTestBaseData();
	}

	@Test
	public void testFromParameterized() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();
		DADLBinding binding = new DADLBinding();

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			List<String> results = aqlImpl.select(query, "", null);

			assertEquals(results.size(), 3);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1984-08-11T19:20:30+08:00");
			assertEquals(d4, "zhangsan");

			DADLParser parser2 = new DADLParser(results.get(1));
			ContentObject contentObj2 = parser2.parse();
			Locatable loc2 = (Locatable) binding.bind(contentObj2);
			String d5 = (String) loc2.itemAtPath("/uid/value");
			String d6 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d7 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d8 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d5, "patient2");
			assertEquals(d6, "F");
			assertEquals(d7, "1986-08-11T19:20:30+08:00");
			assertEquals(d8, "lisi");

			DADLParser parser3 = new DADLParser(results.get(2));
			ContentObject contentObj3 = parser3.parse();
			Locatable loc3 = (Locatable) binding.bind(contentObj3);
			String d9 = (String) loc3.itemAtPath("/uid/value");
			String d10 = (String) loc3
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d11 = (String) loc3
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d12 = (String) loc3
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d9, "patient3");
			assertEquals(d10, "O");
			assertEquals(d11, "1988-08-11T19:20:30+08:00");
			assertEquals(d12, "wangwu");
		}

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/details[at0001]/items[at0009]/value/value = :name";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("name", "lisi");
			List<String> results = aqlImpl.select(query, "", parameters);

			assertEquals(results.size(), 1);

			DADLParser parser2 = new DADLParser(results.get(0));
			ContentObject contentObj2 = parser2.parse();
			Locatable loc2 = (Locatable) binding.bind(contentObj2);
			String d5 = (String) loc2.itemAtPath("/uid/value");
			String d6 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d7 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d8 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d5, "patient2");
			assertEquals(d6, "F");
			assertEquals(d7, "1986-08-11T19:20:30+08:00");
			assertEquals(d8, "lisi");
		}

		{
			String query = "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/uid/value = :name";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("name", "patient1");
			List<String> results = aqlImpl.select(query, "", parameters);

			assertEquals(results.size(), 1);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1984-08-11T19:20:30+08:00");
			assertEquals(d4, "zhangsan");
		}

		cleanTestBaseData();
	}

	@Test
	public void testInsert() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		List<String> dadls = new ArrayList<String>();
		for (String dadl : getDadlFiles()) {
			File file = new File(dadl);
			InputStream is = new FileInputStream(file);
			DADLParser parser = new DADLParser(is);
			ContentObject contentObj = parser.parse();
			DADLBinding binding = new DADLBinding();
			Observation bp = (Observation) binding.bind(contentObj);
			UUID uuid = UUID.randomUUID();
			HierObjectID uid = new HierObjectID(uuid.toString());
			bp.setUid(uid);
			dadls.add(binding.toDADLString(bp));
		}

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();
		aqlImpl.insert(dadls);

		String query = "from openEHR-EHR-OBSERVATION.blood_pressure.v1 as o";
		assertEquals(aqlImpl.select(query, null).size(), 2);

		cleanTestBaseData();
	}

	@Test
	public void testSelect() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();
		DADLBinding binding = new DADLBinding();

		{
			String query = "select o "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o ";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 3);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1984-08-11T19:20:30+08:00");
			assertEquals(d4, "zhangsan");

			DADLParser parser2 = new DADLParser(results.get(1));
			ContentObject contentObj2 = parser2.parse();
			Locatable loc2 = (Locatable) binding.bind(contentObj2);
			String d5 = (String) loc2.itemAtPath("/uid/value");
			String d6 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d7 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d8 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d5, "patient2");
			assertEquals(d6, "F");
			assertEquals(d7, "1986-08-11T19:20:30+08:00");
			assertEquals(d8, "lisi");

			DADLParser parser3 = new DADLParser(results.get(2));
			ContentObject contentObj3 = parser3.parse();
			Locatable loc3 = (Locatable) binding.bind(contentObj3);
			String d9 = (String) loc3.itemAtPath("/uid/value");
			String d10 = (String) loc3
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d11 = (String) loc3
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d12 = (String) loc3
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d9, "patient3");
			assertEquals(d10, "O");
			assertEquals(d11, "1988-08-11T19:20:30+08:00");
			assertEquals(d12, "wangwu");
		}

		{
			String query = "select o "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/details[at0001]/items[at0009]/value/value = 'lisi'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 1);

			DADLParser parser2 = new DADLParser(results.get(0));
			ContentObject contentObj2 = parser2.parse();
			Locatable loc2 = (Locatable) binding.bind(contentObj2);
			String d5 = (String) loc2.itemAtPath("/uid/value");
			String d6 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d7 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d8 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d5, "patient2");
			assertEquals(d6, "F");
			assertEquals(d7, "1986-08-11T19:20:30+08:00");
			assertEquals(d8, "lisi");
		}

		{
			String query = "select o "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/uid/value = 'patient1'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 1);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1984-08-11T19:20:30+08:00");
			assertEquals(d4, "zhangsan");
		}

		{
			String query = "select o "
					+ "from openEHR-EHR-COMPOSITION.visit.v3 as o "
					+ "where o#/context/other_context[at0001]/items[at0015]/value/value = 'patient1'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 2);
		}

		{
			String query = "select o "
					+ "from openEHR-EHR-COMPOSITION.visit.v3 as o "
					+ "where o#/uid/value = 'visit1' and o#/context/other_context[at0001]/items[at0015]/value/value = 'patient1'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 1);
		}

		cleanTestBaseData();
	}

	@Test
	public void testSelectAs() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();
		DADLBinding binding = new DADLBinding();

		{
			String query = "select "
					+ "o#/uid/value as /uid/value, "
					+ "o#/details[at0001]/items[at0003]/value/value as /details[at0001]/items[at0003]/value/value, "
					+ "o#/details[at0001]/items[at0004]/value/value as /details[at0001]/items[at0004]/value/value, "
					+ "o#/details[at0001]/items[at0009]/value/value as /details[at0001]/items[at0009]/value/value "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/details[at0001]/items[at0009]/value/value = 'lisi'";
			String archetypeId = "openEHR-DEMOGRAPHIC-PERSON.patient.v1";
			List<String> results = aqlImpl.select(query, archetypeId);

			assertEquals(results.size(), 1);

			DADLParser parser2 = new DADLParser(results.get(0));
			ContentObject contentObj2 = parser2.parse();
			Locatable loc2 = (Locatable) binding.bind(contentObj2);
			String d5 = (String) loc2.itemAtPath("/uid/value");
			String d6 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d7 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d8 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d5, "patient2");
			assertEquals(d6, "F");
			assertEquals(d7, "1986-08-11T19:20:30+08:00");
			assertEquals(d8, "lisi");
		}

		cleanTestBaseData();
	}

	@Test
	public void testSelectParameterized() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();
		DADLBinding binding = new DADLBinding();

		{
			String query = "select o "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/details[at0001]/items[at0009]/value/value = :Name";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("Name", "lisi");
			List<String> results = aqlImpl.select(query, null, parameters);

			assertEquals(results.size(), 1);

			DADLParser parser2 = new DADLParser(results.get(0));
			ContentObject contentObj2 = parser2.parse();
			Locatable loc2 = (Locatable) binding.bind(contentObj2);
			String d5 = (String) loc2.itemAtPath("/uid/value");
			String d6 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d7 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d8 = (String) loc2
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d5, "patient2");
			assertEquals(d6, "F");
			assertEquals(d7, "1986-08-11T19:20:30+08:00");
			assertEquals(d8, "lisi");
		}

		{
			String query = "select o "
					+ "from openEHR-EHR-COMPOSITION.visit.v3 as o "
					+ "where o#/uid/value = :VisitId and o#/context/other_context[at0001]/items[at0015]/value/value = :PatientId";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("VisitId", "visit1");
			parameters.put("PatientId", "patient1");
			List<String> results = aqlImpl.select(query, null, parameters);

			assertEquals(results.size(), 1);
		}

		cleanTestBaseData();
	}

	@Test
	public void testUpdate() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();
		DADLBinding binding = new DADLBinding();

		{
			String query = "select o "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/uid/value = 'patient1'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 1);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1984-08-11T19:20:30+08:00");
			assertEquals(d4, "zhangsan");
		}

		{
			String query = "update openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o set "
					+ "o#/details[at0001]/items[at0009]/value/value = 'lisi', "
					+ "o#/details[at0001]/items[at0004]/value/value = '1994-08-11T19:20:30+08:00' "
					+ "where o#/uid/value = 'patient1'";

			int ret = aqlImpl.update(query, null);

			assertEquals(ret, 1);
		}

		{
			String query = "select o "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/uid/value = 'patient1'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 1);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1994-08-11T19:20:30+08:00");
			assertEquals(d4, "lisi");
		}

		cleanTestBaseData();
	}

	@Test
	public void testUpdateParameterized() throws Exception {
		reconfigure();

		cleanTestBaseData();
		createTestBaseData();

		AQLExecuteImpl aqlImpl = new AQLExecuteImpl();
		DADLBinding binding = new DADLBinding();

		{
			String query = "select o "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/uid/value = 'patient1'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 1);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1984-08-11T19:20:30+08:00");
			assertEquals(d4, "zhangsan");
		}

		{
			String query = "update openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o set "
					+ "o#/details[at0001]/items[at0009]/value/value = :name, "
					+ "o#/details[at0001]/items[at0004]/value/value = :birthday "
					+ "where o#/uid/value = :pid";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("name", "lisi");
			parameters.put("birthday", "1994-08-11T19:20:30+08:00");
			parameters.put("pid", "patient1");
			int ret = aqlImpl.update(query, parameters);

			assertEquals(ret, 1);
		}

		{
			String query = "select o "
					+ "from openEHR-DEMOGRAPHIC-PERSON.patient.v1 as o "
					+ "where o#/uid/value = 'patient1'";
			List<String> results = aqlImpl.select(query, null);

			assertEquals(results.size(), 1);

			DADLParser parser1 = new DADLParser(results.get(0));
			ContentObject contentObj1 = parser1.parse();
			Locatable loc1 = (Locatable) binding.bind(contentObj1);
			String d1 = (String) loc1.itemAtPath("/uid/value");
			String d2 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0003]/value/value");
			String d3 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0004]/value/value");
			String d4 = (String) loc1
					.itemAtPath("/details[at0001]/items[at0009]/value/value");
			assertEquals(d1, "patient1");
			assertEquals(d2, "M");
			assertEquals(d3, "1994-08-11T19:20:30+08:00");
			assertEquals(d4, "lisi");
		}

		cleanTestBaseData();
	}

}
