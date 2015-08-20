package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.neo4j.graphdb.RelationshipType;

public class Constants {

	public final static String SERVER_ROOT_URI = "http://192.168.12.53:7474/db/data/";
	public final static String nodeEntryPointUri = SERVER_ROOT_URI + "node";

	public enum MyRelationshipType implements RelationshipType {
		SUPERCLASS_OF, HAS_PROPERTY, SUPERTYPE_OF, SUPERRELATION_OF, HAS_DOMAIN, HAS_RANGE
	}

	public static String[] nodeTags = new String[] { "Decision",
			"AbstractPattern", "FeatureWords", "Hamburger", "And", "Not",
			"Root", "StringLength","Contact" };
	public static String[] edgeTags = new String[] { "YesEdge",
			"HamComposeEdge", "AbstractYesEdge", "AndEdge" };

	public static final String[] bilateralMultiplexTypes = new String[] {
			"1-N", "N-1", "1-1", "N-N" };

	public static final String[] excludedAttrOfInstance = new String[] {
			"objectTypeName", "objectTypeId", "lastModifiedTime", "createTime",
			"createUserId", "uri", "localId" };

	public static Boolean isExcludedAttributesOfInstance(String attrName) {
		for (int k = 0; k < excludedAttrOfInstance.length; k++) {
			if (attrName == excludedAttrOfInstance[k]) {
				return true;
			}
		}
		return false;
	}

	public static final String[] unilateralMultiplexTypes = new String[] { "1",
			"N" };

	public static Boolean isValidMultiplex(String multiplexType) {
		for (int i = 0; i < bilateralMultiplexTypes.length; i++) {
			if (multiplexType.equals(bilateralMultiplexTypes[i])) {
				return true;
			}
		}
		for (int i = 0; i < unilateralMultiplexTypes.length; i++) {
			if (multiplexType.equals(unilateralMultiplexTypes[i])) {
				return true;
			}
		}
		return false;
	}

	public static Date getDateByStr(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getStrByDate(Date date) {
		String dateTime = DefaultDateFormat.format(date);
		return dateTime;
	}

	public static String[] systemDataTypeNames = new String[] { "String",
			"int", "long", "float", "double", "boolean", "byte", "Date", "UUID" };

	public static final String[] ConceptRelationTypes = new String[] {
			"SubClassOf", "SuperClassOf", "PropertyOf", "ConceptOf", "PartOf",
			"WholeOf", "EquivalentConcept" };
	public static final int[] FunctionTypes = new int[] { 0, 1, 2 };

	public static final String[] FunctionTypeNames = new String[] { "表达式",
			"JAVA方法", "规则集" };

	public static final String UnknowClassName = "SomeThing";
	public static final int InvalidCompareValue = 1999999999;

	public static int LargestInt = 2147483647;

	public static long LargestLong = 9223372036854775807L;

	public static String LargestDateStr = "2999-12-31 23:59:59";

	public static Date LargestDate = getDateByStr(LargestDateStr);

	public static String SmallestDateStr = "0000-01-01 00:00:00";

	public static Date SmallestDate = getDateByStr(SmallestDateStr);

	public static String FirstSecondOfDay = " 00:00:00";

	public static String LastSecondOfDay = " 23:59:59";

	public static String WebSeparator = "/";

	public static String NameSpaceSeparator = ".";

	public static String Separator = "\\";

	public static int Access_Read = 1;

	public static int Access_Write = 2;

	public static int Access_Delete = 4;

	public static int Access_SubSetInherit = 8;

	public static int Access_Auth = 16;

	public static int Access_StatusWrite = 32;

	public static int Access_SetWrite = 64;

	// public static int Access_AddItem = 128;

	public static int[] AccessRightList = new int[] { Access_Read,
			Access_Write, Access_Delete, Access_SubSetInherit, Access_Auth,
			Access_StatusWrite, Access_SetWrite };

	public static String[] AccessRightNameList = new String[] { "查看", "修改",
			"删除", "继承", "授权", "改变状态", "更改集合内容" };

	public static int MinutesPerManMonth = 21 * 8 * 60;

	public static int MinutesPerManDay = 8 * 60;

	public static int MinutesPerManHour = 60;

	public static String ProductStorageTypeFile = "File";

	public static String ProductStorageTypeOnline = "Online";

	public static String ProductUnitLinkTypeInput = "Input";

	public static String ProductUnitLinkTypeOutput = "Output";

	public static String FileGetUrl = "";

	public static String FileSetUrl = "";

	public static String ClassNamePrefix = "Amray.EIS.BLL.Model.";

	public static String EmptyUUIDStr = "00000000-0000-0000-0000-000000000000";

	public static final UUID EmptyId = UUID.fromString(EmptyUUIDStr);

	public static String InvalidUUIDStr = "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF";

	public static final UUID InvalidId = UUID.fromString(InvalidUUIDStr);

	public static String EmptyObjectIdStr = "000000000000000000000000";

	public static String InvalidObjectIdStr = "FFFFFFFFFFFFFFFFFFFFFFFF";

	public static String[] symbolList = new String[] { ":", ";", "'", ",", ".",
			"/", "?", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-",
			"_", "+", "=", "\"", "|", "\\", "，", "；", "。", "？", "！", "“", "”",
			"：", "（", "）" };

	public static String[] subSentenceEndSymbolList = new String[] { "。", "？",
			"！", "\n", ". ", "? ", "! ", ":", ";", ",", "，", "；", "：", "\"",
			"“", "”", "'", "‘", "’", "(", ")", "（", "）" };

	public static String[] sentenceEndSymbolList = new String[] { "。", "？",
			"！", "\n", ". ", "? ", "! " };

	public static String[] wideSymbolList = new String[] { "ａ", "ｂ", "ｃ", "ｄ",
			"ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ", "ｏ", "ｐ", "ｑ",
			"ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ", "Ａ", "Ｂ", "Ｃ", "Ｄ",
			"Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ", "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ",
			"Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ", "Ｙ", "Ｚ", "～", "！", "＠", "＃",
			"￥", "％", "…", "＆", "×", "（", "）", "—", "＋", "｜", "·", "１", "２",
			"３", "４", "５", "６", "７", "８", "９", "０", "－", "＝", "＼", "｛", "｝",
			"：", "”", "《", "》", "？", "【", "】", "；", "’", "，", "。", "、" };

	public static String SetOperatorIntersect = "##INTERSECT##";

	public static String SetOperatorUnion = "##UNION##";

	public static String SetOperatorComplement = "##COMPLEMENT##";

	public static String SetOperatorSubset = "##SUBSET##";

	public static String SeedDocumentXml = "<LodeDocument xmlns=\"http://lode.amray.cn\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xsi:type=\"LodeDocument.MarkedDocument\" version=\"1\"></LodeDocument>";

	public static String SeedDocumentHtml = "<html></html>";

	public static String SeedNICTCLASDocumentXml = "<NICTCLAS></NICTCLAS>";

	public static String SeedOntologyXml = "<RDF></RDF>";

	public static String SeedDataXml = "<ClientLodeData></ClientLodeData>";

	public static int SameDomainTrustDegree = 500;

	public static int RelatedDomainTrustDegree = 300;

	public static int NonDomainTrustDegree = 100;

	public static String DefaultEncodingName = "gb2312";

	public static String FileURLPrefix = "file:///";

	public static String OWLClassURISeperator = "#";

	public static String OWLMetaName = "OWLMeta";

	public static String OWLAnnotationName = "OWLAnnotation";

	public static String OWLClassName = "OWLClass";

	public static String OWLInstanceName = "OWLInstance";

	public static String OWLObjectPropertyName = "OWLObjectProperty";

	public static String OWLDataPropertyName = "OWLDataProperty";

	public static String OWLSWRLRuleName = "OWLSWRLRule";

	public static String OWLOtherEntiryName = "OWLOther";

	public static String OWLUnknowEntiryName = "OWLUnknow";

	public static String XMLDataType_String = "String";

	public static String XMLDataType_Boolean = "boolean";

	public static String XMLDataType_Float = "float";

	public static String XMLDataType_Integer = "int";

	public static String XMLDataType_DateTime = "dateTime";

	public static String[] XMLDataTypeList = new String[] { XMLDataType_String,
			XMLDataType_Boolean, XMLDataType_Float, XMLDataType_Integer,
			XMLDataType_DateTime };

	public static String UserAuthenticationTypeName_WindowsIntegrated = "Integrated";

	public static String UserAuthenticationTypeName_LodeServer = "LodeServer";

	public static String[] SettingsSeperators = new String[] { ";", "," };

	public static String[] SettingItemsSeperators = new String[] { "=" };

	public static String LocalHostIPAddress = "127.0.0.1";

	public static String OWLFileExtension = ".owl";

	public static String XMLFileExtension = ".xml";

	public static String TextFileExtension = ".txt";

	public static String FileCachePathName = "FileCache";

	public static String HTMLFileExtension = ".html";

	public static String PackageFileExtension = ".jar";

	public static String[] LanguageCodeList = new String[] { "zh-CN", "en-US" };

	public static String OWLDocumentBodyPrefix = "rdf:RDF";

	public static String OWLClassPrefix = "owl:Class";

	public static String OWLClassRDFIdPrefix = "rdf:ID";

	public static String RDFNameSpaceURL = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

	public static String RDFSNameSpaceURL = "http://www.w3.org/2000/01/rdf-schema#";

	public static String XSDNameSpaceURL = "http://www.w3.org/2001/XMLSchema#";

	public static String OWLNameSpaceURL = "http://www.w3.org/2002/07/owl# ";

	public static int OWLFileMinumLength = 950;

	public static String[] VirtualWordList = new String[] { "把", "被", "并",
			"并且", "的", "等", "等等", "对", "对于", "给", "跟", "过", "和", "或", "或者",
			"或是", "及", "及其", "己", "且", "与", "之", "着", "了" };

	public static String[] WordRelationPredicateList = new String[] {
			"___DRNB___", "___SMSB___", "___SMST___", "___SMSG___",
			"___ASRT___", "___ASRU___" };

	public static int[] WordRelationPredicateDistanceList = new int[] { 2, 3,
			4, 6, 1, 1 };

	public static String[] wordSplitSymbolList = new String[] { "\\", " ",
			"\t", "。", "？", "！", "\n", ". ", "? ", "! ", ":", ";", ",", "，",
			"；", "：", "\"", "“", "”", "'", "‘", "’", "(", ")", "（", "）" };

	public static String[] videoExtensionList = new String[] { ".avi", ".rm",
			".mov", ".asf", ".rmvb", ".wmv", ".mpeg", ".mpg" };

	public static String[] pictureExtensionList = new String[] { ".gif",
			".png", ".jpeg", ".jpg", ".bmp", ".pcx" };

	public static String[] prohibitExtensionList = new String[] { ".exe",
			".dll", ".com", ".scr", ".vxd", ".sys", ".cgi", ".php", ".asp",
			".aspx", ".php", ".bat", ".jsp", ".java", ".class" };

	public static int MinutesPerHour = 60;

	public static int MinutesPerMonth = 71 * 12 * 60;

	public static int MinutesPerWeek = 7 * 24 * 60;

	public static int MinutesPerDay = 8 * 60;

	public static float WorkTimeToManMonth(int plannedWorkTime) {
		int manmonth100 = (int) (((float) plannedWorkTime)
				/ ((float) (MinutesPerManMonth)) * 100 + 0.5);
		return ((float) manmonth100) / 100;
	}

	public static float WorkTimeToManDay(int plannedWorkTime) {
		int manday100 = (int) (((float) plannedWorkTime)
				/ ((float) (MinutesPerManDay)) * 100 + 0.5);
		return ((float) manday100) / 100;
	}

	public static float WorkTimeToManHour(int plannedWorkTime) {
		int manhour100 = (int) (((float) plannedWorkTime)
				/ ((float) (MinutesPerManHour)) * 100 + 0.5);
		return ((float) manhour100) / 100;
	}

	public static int ManMonthToWorkTime(float manmonth) {
		return (int) (manmonth * ((float) (MinutesPerManMonth)));
	}

	public static int ManDayToWorkTime(float manday) {
		return (int) (manday * ((float) (MinutesPerManDay)));
	}

	public static int ManHourToWorkTime(float manhour) {
		return (int) (manhour * ((float) (MinutesPerManHour)));
	}

	public static float TimespanToMonth(int timespan) {
		int manmonth100 = (int) (((float) timespan)
				/ ((float) (MinutesPerMonth)) * 100 + 0.5);
		return ((float) manmonth100) / 100;
	}

	public static float TimespanToWeek(int timespan) {
		int manmonth100 = (int) (((float) timespan)
				/ ((float) (MinutesPerWeek)) * 100 + 0.5);
		return ((float) manmonth100) / 100;
	}

	public static float TimespanToDay(int timespan) {
		int manday100 = (int) (((float) timespan) / ((float) (MinutesPerDay))
				* 100 + 0.5);
		return ((float) manday100) / 100;
	}

	public static float TimespanToHour(int timespan) {
		int manhour100 = (int) (((float) timespan) / ((float) (MinutesPerHour))
				* 100 + 0.5);
		return ((float) manhour100) / 100;
	}

	public static int MonthToTimespan(float manmonth) {
		return (int) (manmonth * ((float) (MinutesPerMonth)));
	}

	public static int WeekToTimespan(float weeknum) {
		return (int) (weeknum * ((float) (MinutesPerWeek)));
	}

	public static int DayToTimespan(float manday) {
		return (int) (manday * ((float) (MinutesPerDay)));
	}

	public static int HourToTimespan(float manhour) {
		return (int) (manhour * ((float) (MinutesPerHour)));
	}

	public static String DefaultLanguageCode = "zh-CN";

	public static String DefaultLanguageName = "简体中文";

	public static String ObjectIdSuffix = "Id";

	public static String ObjectNameSuffix = "Name";

	public static String GetMethodPrefix = "get";

	public static String SetMethodPrefix = "set";

	public static String SaveMethodPrefix = "save";

	public static String DeleteMethodPrefix = "delete";

	public static String toPlural(String theWord) {
		if (theWord.endsWith("s")) {
			return theWord + "es";
		}
		if (theWord.endsWith("y")) {
			return theWord.substring(0, theWord.length() - 1) + "ies";
		}
		return theWord + "s";
	}

	public static String[] DataSourceTypeNames = new String[] { "mysql5",
			"mongodb", "oracle11g", "mssql7", "mssql8", "mysql3", "postgres8",
			"xml/xsd", "access97", "oracle9", "sqllite" };

	public static String ModelPackageName = "model";

	public static String ServiceNameSuffix = "Service";

	public static String DAOTypeSuffix = "DAO";

	// public static String ToPlural(String theWord) {
	// if (theWord.endsWith("s")) {
	// return theWord + "es";
	// }
	// if (theWord.endsWith("y")) {
	// return theWord.substring(0, theWord.length() - 1) + "ies";
	// }
	// if (theWord.endsWith("um")) {
	// return theWord.substring(0, theWord.length() - 2) + "a";
	// }
	// return theWord + "s";
	// }
	//
	// public static String ToSingular(String theWord) {
	// if (theWord.endsWith("ies")) {
	// return theWord.substring(0, theWord.length() - 3) + "y";
	// }
	// if (theWord.endsWith("a")) {
	// return theWord.substring(0, theWord.length() - 1) + "um";
	// }
	// if (theWord.endsWith("ses")) {
	// return theWord.substring(0, theWord.length() - 2);
	// }
	// if (theWord.endsWith("thes")) {
	// return theWord.substring(0, theWord.length() - 2);
	// }
	// return theWord.substring(0, theWord.length() - 1);
	// }

	public static String DataSizeShort = "Short";

	public static String DataSizeMiddle = "Middle";

	public static String DataSizeLong = "Long";

	public static String DataSizeHuge = "Huge";

	public static String RootUserName = "Admin";

	public static String AppKey = "5830254587056012969";

	public static final String DataOpCreate = "DOP_CREATE";
	public static final String DataOpDelete = "DOP_DELETE";
	public static final String DataOpUpdate = "DOP_UPDATE";
	public static final String DataOpAppend = "DOP_APPEND";
	public static final String DataOpRemove = "DOP_REMOVE";
	public static final String DataOpClear = "DOP_CLEAR";
	public static final String ProofTypeOrig = "PRF_ORG";

	public static final long DefaultObjectTypeRootId = 1;
	public static final long DefaultRelationTypeRootId = 2;
	public static final long DefaultPropertyTypeRootId = 3;

	public static final String DefaultcreateUserId = "1";

	public static final SimpleDateFormat DefaultDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss | SSS");
}
