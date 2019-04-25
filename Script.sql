--<ScriptOptions statementTerminator=";"/>

CREATE TABLE pg_type (
		typname CIDR(2147483647) NOT NULL,
		typnamespace OID NOT NULL,
		typowner OID NOT NULL,
		typlen INT2 NOT NULL,
		typbyval BOOL NOT NULL,
		typtype BPCHAR(1) NOT NULL,
		typcategory BPCHAR(1) NOT NULL,
		typispreferred BOOL NOT NULL,
		typisdefined BOOL NOT NULL,
		typdelim BPCHAR(1) NOT NULL,
		typrelid OID NOT NULL,
		typelem OID NOT NULL,
		typarray OID NOT NULL,
		typinput REGPROC NOT NULL,
		typoutput REGPROC NOT NULL,
		typreceive REGPROC NOT NULL,
		typsend REGPROC NOT NULL,
		typmodin REGPROC NOT NULL,
		typmodout REGPROC NOT NULL,
		typanalyze REGPROC NOT NULL,
		typalign BPCHAR(1) NOT NULL,
		typstorage BPCHAR(1) NOT NULL,
		typnotnull BOOL NOT NULL,
		typbasetype OID NOT NULL,
		typtypmod INT4 NOT NULL,
		typndims INT4 NOT NULL,
		typcollation OID NOT NULL,
		typdefaultbin null,
		typdefault TEXT(2147483647),
		typacl ACLITEM[ ]
	);

CREATE TABLE pg_constraint (
		conname CIDR(2147483647) NOT NULL,
		connamespace OID NOT NULL,
		contype BPCHAR(1) NOT NULL,
		condeferrable BOOL NOT NULL,
		condeferred BOOL NOT NULL,
		convalidated BOOL NOT NULL,
		conrelid OID NOT NULL,
		contypid OID NOT NULL,
		conindid OID NOT NULL,
		conparentid OID NOT NULL,
		confrelid OID NOT NULL,
		confupdtype BPCHAR(1) NOT NULL,
		confdeltype BPCHAR(1) NOT NULL,
		confmatchtype BPCHAR(1) NOT NULL,
		conislocal BOOL NOT NULL,
		coninhcount INT4 NOT NULL,
		connoinherit BOOL NOT NULL,
		conkey INT2[ ],
		confkey INT2[ ],
		conpfeqop OID[ ],
		conppeqop OID[ ],
		conffeqop OID[ ],
		conexclop OID[ ],
		conbin null,
		consrc TEXT(2147483647)
	);

CREATE TABLE pg_tablespace (
		spcname CIDR(2147483647) NOT NULL,
		spcowner OID NOT NULL,
		spcacl ACLITEM[ ],
		spcoptions TEXT[ ](2147483647)
	);

CREATE TABLE pg_trigger (
		tgrelid OID NOT NULL,
		tgname CIDR(2147483647) NOT NULL,
		tgfoid OID NOT NULL,
		tgtype INT2 NOT NULL,
		tgenabled BPCHAR(1) NOT NULL,
		tgisinternal BOOL NOT NULL,
		tgconstrrelid OID NOT NULL,
		tgconstrindid OID NOT NULL,
		tgconstraint OID NOT NULL,
		tgdeferrable BOOL NOT NULL,
		tginitdeferred BOOL NOT NULL,
		tgnargs INT2 NOT NULL,
		tgattr null NOT NULL,
		tgargs BYTEA(2147483647) NOT NULL,
		tgqual null,
		tgoldtable CIDR(2147483647),
		tgnewtable CIDR(2147483647)
	);

CREATE TABLE pg_range (
		rngtypid OID NOT NULL,
		rngsubtype OID NOT NULL,
		rngcollation OID NOT NULL,
		rngsubopc OID NOT NULL,
		rngcanonical REGPROC NOT NULL,
		rngsubdiff REGPROC NOT NULL
	);

CREATE TABLE pg_attrdef (
		adrelid OID NOT NULL,
		adnum INT2 NOT NULL,
		adbin null,
		adsrc TEXT(2147483647)
	);

CREATE TABLE pg_shdescription (
		objoid OID NOT NULL,
		classoid OID NOT NULL,
		description TEXT(2147483647) NOT NULL
	);

CREATE TABLE pg_partitioned_table (
		partrelid OID NOT NULL,
		partstrat BPCHAR(1) NOT NULL,
		partnatts INT2 NOT NULL,
		partdefid OID NOT NULL,
		partattrs null NOT NULL,
		partclass null NOT NULL,
		partcollation null NOT NULL,
		partexprs null
	);

CREATE TABLE sql_parts (
		feature_id null,
		feature_name null,
		is_supported null,
		is_verified_by null,
		comments null
	);

CREATE TABLE pg_ts_template (
		tmplname CIDR(2147483647) NOT NULL,
		tmplnamespace OID NOT NULL,
		tmplinit REGPROC NOT NULL,
		tmpllexize REGPROC NOT NULL
	);

CREATE TABLE pg_statistic (
		starelid OID NOT NULL,
		staattnum INT2 NOT NULL,
		stainherit BOOL NOT NULL,
		stanullfrac FLOAT4 NOT NULL,
		stawidth INT4 NOT NULL,
		stadistinct FLOAT4 NOT NULL,
		stakind1 INT2 NOT NULL,
		stakind2 INT2 NOT NULL,
		stakind3 INT2 NOT NULL,
		stakind4 INT2 NOT NULL,
		stakind5 INT2 NOT NULL,
		staop1 OID NOT NULL,
		staop2 OID NOT NULL,
		staop3 OID NOT NULL,
		staop4 OID NOT NULL,
		staop5 OID NOT NULL,
		stanumbers1 FLOAT4[ ],
		stanumbers2 FLOAT4[ ],
		stanumbers3 FLOAT4[ ],
		stanumbers4 FLOAT4[ ],
		stanumbers5 FLOAT4[ ],
		stavalues1 null,
		stavalues2 null,
		stavalues3 null,
		stavalues4 null,
		stavalues5 null
	);

CREATE TABLE pg_user_mapping (
		umuser OID NOT NULL,
		umserver OID NOT NULL,
		umoptions TEXT[ ](2147483647)
	);

CREATE TABLE pg_language (
		lanname CIDR(2147483647) NOT NULL,
		lanowner OID NOT NULL,
		lanispl BOOL NOT NULL,
		lanpltrusted BOOL NOT NULL,
		lanplcallfoid OID NOT NULL,
		laninline OID NOT NULL,
		lanvalidator OID NOT NULL,
		lanacl ACLITEM[ ]
	);

CREATE TABLE pg_ts_parser (
		prsname CIDR(2147483647) NOT NULL,
		prsnamespace OID NOT NULL,
		prsstart REGPROC NOT NULL,
		prstoken REGPROC NOT NULL,
		prsend REGPROC NOT NULL,
		prsheadline REGPROC NOT NULL,
		prslextype REGPROC NOT NULL
	);

CREATE TABLE pg_largeobject (
		loid OID NOT NULL,
		pageno INT4 NOT NULL,
		data BYTEA(2147483647) NOT NULL
	);

CREATE TABLE pg_event_trigger (
		evtname CIDR(2147483647) NOT NULL,
		evtevent CIDR(2147483647) NOT NULL,
		evtowner OID NOT NULL,
		evtfoid OID NOT NULL,
		evtenabled BPCHAR(1) NOT NULL,
		evttags TEXT[ ](2147483647)
	);

CREATE TABLE pg_cast (
		castsource OID NOT NULL,
		casttarget OID NOT NULL,
		castfunc OID NOT NULL,
		castcontext BPCHAR(1) NOT NULL,
		castmethod BPCHAR(1) NOT NULL
	);

CREATE TABLE pg_foreign_table (
		ftrelid OID NOT NULL,
		ftserver OID NOT NULL,
		ftoptions TEXT[ ](2147483647)
	);

CREATE TABLE sql_packages (
		feature_id null,
		feature_name null,
		is_supported null,
		is_verified_by null,
		comments null
	);

CREATE TABLE pg_index (
		indexrelid OID NOT NULL,
		indrelid OID NOT NULL,
		indnatts INT2 NOT NULL,
		indnkeyatts INT2 NOT NULL,
		indisunique BOOL NOT NULL,
		indisprimary BOOL NOT NULL,
		indisexclusion BOOL NOT NULL,
		indimmediate BOOL NOT NULL,
		indisclustered BOOL NOT NULL,
		indisvalid BOOL NOT NULL,
		indcheckxmin BOOL NOT NULL,
		indisready BOOL NOT NULL,
		indislive BOOL NOT NULL,
		indisreplident BOOL NOT NULL,
		indkey null NOT NULL,
		indcollation null NOT NULL,
		indclass null NOT NULL,
		indoption null NOT NULL,
		indexprs null,
		indpred null
	);

CREATE TABLE pg_namespace (
		nspname CIDR(2147483647) NOT NULL,
		nspowner OID NOT NULL,
		nspacl ACLITEM[ ]
	);

CREATE TABLE pg_opclass (
		opcmethod OID NOT NULL,
		opcname CIDR(2147483647) NOT NULL,
		opcnamespace OID NOT NULL,
		opcowner OID NOT NULL,
		opcfamily OID NOT NULL,
		opcintype OID NOT NULL,
		opcdefault BOOL NOT NULL,
		opckeytype OID NOT NULL
	);

CREATE TABLE pg_ts_config_map (
		mapcfg OID NOT NULL,
		maptokentype INT4 NOT NULL,
		mapseqno INT4 NOT NULL,
		mapdict OID NOT NULL
	);

CREATE TABLE pg_pltemplate (
		tmplname CIDR(2147483647) NOT NULL,
		tmpltrusted BOOL NOT NULL,
		tmpldbacreate BOOL NOT NULL,
		tmplhandler TEXT(2147483647) NOT NULL,
		tmplinline TEXT(2147483647),
		tmplvalidator TEXT(2147483647),
		tmpllibrary TEXT(2147483647) NOT NULL,
		tmplacl ACLITEM[ ]
	);

CREATE TABLE pg_largeobject_metadata (
		lomowner OID NOT NULL,
		lomacl ACLITEM[ ]
	);

CREATE TABLE pg_shdepend (
		dbid OID NOT NULL,
		classid OID NOT NULL,
		objid OID NOT NULL,
		objsubid INT4 NOT NULL,
		refclassid OID NOT NULL,
		refobjid OID NOT NULL,
		deptype BPCHAR(1) NOT NULL
	);

CREATE TABLE pg_amproc (
		amprocfamily OID NOT NULL,
		amproclefttype OID NOT NULL,
		amprocrighttype OID NOT NULL,
		amprocnum INT2 NOT NULL,
		amproc REGPROC NOT NULL
	);

CREATE TABLE sql_languages (
		sql_language_source null,
		sql_language_year null,
		sql_language_conformance null,
		sql_language_integrity null,
		sql_language_implementation null,
		sql_language_binding_style null,
		sql_language_programming_language null
	);

CREATE TABLE pg_collation (
		collname CIDR(2147483647) NOT NULL,
		collnamespace OID NOT NULL,
		collowner OID NOT NULL,
		collprovider BPCHAR(1) NOT NULL,
		collencoding INT4 NOT NULL,
		collcollate CIDR(2147483647) NOT NULL,
		collctype CIDR(2147483647) NOT NULL,
		collversion TEXT(2147483647)
	);

CREATE TABLE pg_init_privs (
		objoid OID NOT NULL,
		classoid OID NOT NULL,
		objsubid INT4 NOT NULL,
		privtype BPCHAR(1) NOT NULL,
		initprivs ACLITEM[ ] NOT NULL
	);

CREATE TABLE sql_sizing (
		sizing_id null,
		sizing_name null,
		supported_value null,
		comments null
	);

CREATE TABLE pg_rewrite (
		rulename CIDR(2147483647) NOT NULL,
		ev_class OID NOT NULL,
		ev_type BPCHAR(1) NOT NULL,
		ev_enabled BPCHAR(1) NOT NULL,
		is_instead BOOL NOT NULL,
		ev_qual null,
		ev_action null
	);

CREATE TABLE pg_publication_rel (
		prpubid OID NOT NULL,
		prrelid OID NOT NULL
	);

CREATE TABLE pg_opfamily (
		opfmethod OID NOT NULL,
		opfname CIDR(2147483647) NOT NULL,
		opfnamespace OID NOT NULL,
		opfowner OID NOT NULL
	);

CREATE TABLE pg_seclabel (
		objoid OID NOT NULL,
		classoid OID NOT NULL,
		objsubid INT4 NOT NULL,
		provider TEXT(2147483647) NOT NULL,
		label TEXT(2147483647) NOT NULL
	);

CREATE TABLE pg_publication (
		pubname CIDR(2147483647) NOT NULL,
		pubowner OID NOT NULL,
		puballtables BOOL NOT NULL,
		pubinsert BOOL NOT NULL,
		pubupdate BOOL NOT NULL,
		pubdelete BOOL NOT NULL,
		pubtruncate BOOL NOT NULL
	);

CREATE TABLE pg_ts_dict (
		dictname CIDR(2147483647) NOT NULL,
		dictnamespace OID NOT NULL,
		dictowner OID NOT NULL,
		dicttemplate OID NOT NULL,
		dictinitoption TEXT(2147483647)
	);

CREATE TABLE pg_am (
		amname CIDR(2147483647) NOT NULL,
		amhandler REGPROC NOT NULL,
		amtype BPCHAR(1) NOT NULL
	);

CREATE TABLE pg_aggregate (
		aggfnoid REGPROC NOT NULL,
		aggkind BPCHAR(1) NOT NULL,
		aggnumdirectargs INT2 NOT NULL,
		aggtransfn REGPROC NOT NULL,
		aggfinalfn REGPROC NOT NULL,
		aggcombinefn REGPROC NOT NULL,
		aggserialfn REGPROC NOT NULL,
		aggdeserialfn REGPROC NOT NULL,
		aggmtransfn REGPROC NOT NULL,
		aggminvtransfn REGPROC NOT NULL,
		aggmfinalfn REGPROC NOT NULL,
		aggfinalextra BOOL NOT NULL,
		aggmfinalextra BOOL NOT NULL,
		aggfinalmodify BPCHAR(1) NOT NULL,
		aggmfinalmodify BPCHAR(1) NOT NULL,
		aggsortop OID NOT NULL,
		aggtranstype OID NOT NULL,
		aggtransspace INT4 NOT NULL,
		aggmtranstype OID NOT NULL,
		aggmtransspace INT4 NOT NULL,
		agginitval TEXT(2147483647),
		aggminitval TEXT(2147483647)
	);

CREATE TABLE pg_sequence (
		seqrelid OID NOT NULL,
		seqtypid OID NOT NULL,
		seqstart INT8 NOT NULL,
		seqincrement INT8 NOT NULL,
		seqmax INT8 NOT NULL,
		seqmin INT8 NOT NULL,
		seqcache INT8 NOT NULL,
		seqcycle BOOL NOT NULL
	);

CREATE TABLE pg_db_role_setting (
		setdatabase OID NOT NULL,
		setrole OID NOT NULL,
		setconfig TEXT[ ](2147483647)
	);

CREATE TABLE pg_description (
		objoid OID NOT NULL,
		classoid OID NOT NULL,
		objsubid INT4 NOT NULL,
		description TEXT(2147483647) NOT NULL
	);

CREATE TABLE sql_features (
		feature_id null,
		feature_name null,
		sub_feature_id null,
		sub_feature_name null,
		is_supported null,
		is_verified_by null,
		comments null
	);

CREATE TABLE pg_enum (
		enumtypid OID NOT NULL,
		enumsortorder FLOAT4 NOT NULL,
		enumlabel CIDR(2147483647) NOT NULL
	);

CREATE TABLE pg_shseclabel (
		objoid OID NOT NULL,
		classoid OID NOT NULL,
		provider TEXT(2147483647) NOT NULL,
		label TEXT(2147483647) NOT NULL
	);

CREATE TABLE pg_operator (
		oprname CIDR(2147483647) NOT NULL,
		oprnamespace OID NOT NULL,
		oprowner OID NOT NULL,
		oprkind BPCHAR(1) NOT NULL,
		oprcanmerge BOOL NOT NULL,
		oprcanhash BOOL NOT NULL,
		oprleft OID NOT NULL,
		oprright OID NOT NULL,
		oprresult OID NOT NULL,
		oprcom OID NOT NULL,
		oprnegate OID NOT NULL,
		oprcode REGPROC NOT NULL,
		oprrest REGPROC NOT NULL,
		oprjoin REGPROC NOT NULL
	);

CREATE TABLE pg_depend (
		classid OID NOT NULL,
		objid OID NOT NULL,
		objsubid INT4 NOT NULL,
		refclassid OID NOT NULL,
		refobjid OID NOT NULL,
		refobjsubid INT4 NOT NULL,
		deptype BPCHAR(1) NOT NULL
	);

CREATE TABLE pg_amop (
		amopfamily OID NOT NULL,
		amoplefttype OID NOT NULL,
		amoprighttype OID NOT NULL,
		amopstrategy INT2 NOT NULL,
		amoppurpose BPCHAR(1) NOT NULL,
		amopopr OID NOT NULL,
		amopmethod OID NOT NULL,
		amopsortfamily OID NOT NULL
	);

CREATE TABLE pg_ts_config (
		cfgname CIDR(2147483647) NOT NULL,
		cfgnamespace OID NOT NULL,
		cfgowner OID NOT NULL,
		cfgparser OID NOT NULL
	);

CREATE TABLE pg_default_acl (
		defaclrole OID NOT NULL,
		defaclnamespace OID NOT NULL,
		defaclobjtype BPCHAR(1) NOT NULL,
		defaclacl ACLITEM[ ]
	);

CREATE TABLE pg_subscription_rel (
		srsubid OID NOT NULL,
		srrelid OID NOT NULL,
		srsubstate BPCHAR(1) NOT NULL,
		srsublsn null NOT NULL
	);

CREATE TABLE pg_conversion (
		conname CIDR(2147483647) NOT NULL,
		connamespace OID NOT NULL,
		conowner OID NOT NULL,
		conforencoding INT4 NOT NULL,
		contoencoding INT4 NOT NULL,
		conproc REGPROC NOT NULL,
		condefault BOOL NOT NULL
	);

CREATE TABLE pg_transform (
		trftype OID NOT NULL,
		trflang OID NOT NULL,
		trffromsql REGPROC NOT NULL,
		trftosql REGPROC NOT NULL
	);

CREATE TABLE employees (
		id SERIAL DEFAULT nextval('employees_id_seq'::regclass) NOT NULL,
		first_name TEXT(2147483647),
		last_name TEXT(2147483647)
	);

CREATE TABLE pg_foreign_data_wrapper (
		fdwname CIDR(2147483647) NOT NULL,
		fdwowner OID NOT NULL,
		fdwhandler OID NOT NULL,
		fdwvalidator OID NOT NULL,
		fdwacl ACLITEM[ ],
		fdwoptions TEXT[ ](2147483647)
	);

CREATE TABLE pg_attribute (
		attrelid OID NOT NULL,
		attname CIDR(2147483647) NOT NULL,
		atttypid OID NOT NULL,
		attstattarget INT4 NOT NULL,
		attlen INT2 NOT NULL,
		attnum INT2 NOT NULL,
		attndims INT4 NOT NULL,
		attcacheoff INT4 NOT NULL,
		atttypmod INT4 NOT NULL,
		attbyval BOOL NOT NULL,
		attstorage BPCHAR(1) NOT NULL,
		attalign BPCHAR(1) NOT NULL,
		attnotnull BOOL NOT NULL,
		atthasdef BOOL NOT NULL,
		atthasmissing BOOL NOT NULL,
		attidentity BPCHAR(1) NOT NULL,
		attisdropped BOOL NOT NULL,
		attislocal BOOL NOT NULL,
		attinhcount INT4 NOT NULL,
		attcollation OID NOT NULL,
		attacl ACLITEM[ ],
		attoptions TEXT[ ](2147483647),
		attfdwoptions TEXT[ ](2147483647),
		attmissingval null
	);

CREATE TABLE pg_proc (
		proname CIDR(2147483647) NOT NULL,
		pronamespace OID NOT NULL,
		proowner OID NOT NULL,
		prolang OID NOT NULL,
		procost FLOAT4 NOT NULL,
		prorows FLOAT4 NOT NULL,
		provariadic OID NOT NULL,
		protransform REGPROC NOT NULL,
		prokind BPCHAR(1) NOT NULL,
		prosecdef BOOL NOT NULL,
		proleakproof BOOL NOT NULL,
		proisstrict BOOL NOT NULL,
		proretset BOOL NOT NULL,
		provolatile BPCHAR(1) NOT NULL,
		proparallel BPCHAR(1) NOT NULL,
		pronargs INT2 NOT NULL,
		pronargdefaults INT2 NOT NULL,
		prorettype OID NOT NULL,
		proargtypes null NOT NULL,
		proallargtypes OID[ ],
		proargmodes ABSTIME[ ],
		proargnames TEXT[ ](2147483647),
		proargdefaults null,
		protrftypes OID[ ],
		prosrc TEXT(2147483647) NOT NULL,
		probin TEXT(2147483647),
		proconfig TEXT[ ](2147483647),
		proacl ACLITEM[ ]
	);

CREATE TABLE pg_extension (
		extname CIDR(2147483647) NOT NULL,
		extowner OID NOT NULL,
		extnamespace OID NOT NULL,
		extrelocatable BOOL NOT NULL,
		extversion TEXT(2147483647) NOT NULL,
		extconfig OID[ ],
		extcondition TEXT[ ](2147483647)
	);

CREATE TABLE pg_inherits (
		inhrelid OID NOT NULL,
		inhparent OID NOT NULL,
		inhseqno INT4 NOT NULL
	);

CREATE TABLE sql_sizing_profiles (
		sizing_id null,
		sizing_name null,
		profile_id null,
		required_value null,
		comments null
	);

CREATE TABLE pg_replication_origin (
		roident OID NOT NULL,
		roname TEXT(2147483647) NOT NULL
	);

CREATE TABLE pg_auth_members (
		roleid OID NOT NULL,
		member OID NOT NULL,
		grantor OID NOT NULL,
		admin_option BOOL NOT NULL
	);

CREATE TABLE pg_foreign_server (
		srvname CIDR(2147483647) NOT NULL,
		srvowner OID NOT NULL,
		srvfdw OID NOT NULL,
		srvtype TEXT(2147483647),
		srvversion TEXT(2147483647),
		srvacl ACLITEM[ ],
		srvoptions TEXT[ ](2147483647)
	);

CREATE TABLE pg_subscription (
		subdbid OID NOT NULL,
		subname CIDR(2147483647) NOT NULL,
		subowner OID NOT NULL,
		subenabled BOOL NOT NULL,
		subconninfo TEXT(2147483647) NOT NULL,
		subslotname CIDR(2147483647) NOT NULL,
		subsynccommit TEXT(2147483647) NOT NULL,
		subpublications TEXT[ ](2147483647) NOT NULL
	);

CREATE TABLE pg_authid (
		rolname CIDR(2147483647) NOT NULL,
		rolsuper BOOL NOT NULL,
		rolinherit BOOL NOT NULL,
		rolcreaterole BOOL NOT NULL,
		rolcreatedb BOOL NOT NULL,
		rolcanlogin BOOL NOT NULL,
		rolreplication BOOL NOT NULL,
		rolbypassrls BOOL NOT NULL,
		rolconnlimit INT4 NOT NULL,
		rolpassword TEXT(2147483647),
		rolvaliduntil TIMESTAMPTZ
	);

CREATE TABLE sql_implementation_info (
		implementation_info_id null,
		implementation_info_name null,
		integer_value null,
		character_value null,
		comments null
	);

CREATE TABLE pg_database (
		datname CIDR(2147483647) NOT NULL,
		datdba OID NOT NULL,
		encoding INT4 NOT NULL,
		datcollate CIDR(2147483647) NOT NULL,
		datctype CIDR(2147483647) NOT NULL,
		datistemplate BOOL NOT NULL,
		datallowconn BOOL NOT NULL,
		datconnlimit INT4 NOT NULL,
		datlastsysoid OID NOT NULL,
		datfrozenxid XID NOT NULL,
		datminmxid XID NOT NULL,
		dattablespace OID NOT NULL,
		datacl ACLITEM[ ]
	);

CREATE TABLE pg_class (
		relname CIDR(2147483647) NOT NULL,
		relnamespace OID NOT NULL,
		reltype OID NOT NULL,
		reloftype OID NOT NULL,
		relowner OID NOT NULL,
		relam OID NOT NULL,
		relfilenode OID NOT NULL,
		reltablespace OID NOT NULL,
		relpages INT4 NOT NULL,
		reltuples FLOAT4 NOT NULL,
		relallvisible INT4 NOT NULL,
		reltoastrelid OID NOT NULL,
		relhasindex BOOL NOT NULL,
		relisshared BOOL NOT NULL,
		relpersistence BPCHAR(1) NOT NULL,
		relkind BPCHAR(1) NOT NULL,
		relnatts INT2 NOT NULL,
		relchecks INT2 NOT NULL,
		relhasoids BOOL NOT NULL,
		relhasrules BOOL NOT NULL,
		relhastriggers BOOL NOT NULL,
		relhassubclass BOOL NOT NULL,
		relrowsecurity BOOL NOT NULL,
		relforcerowsecurity BOOL NOT NULL,
		relispopulated BOOL NOT NULL,
		relreplident BPCHAR(1) NOT NULL,
		relispartition BOOL NOT NULL,
		relrewrite OID NOT NULL,
		relfrozenxid XID NOT NULL,
		relminmxid XID NOT NULL,
		relacl ACLITEM[ ],
		reloptions TEXT[ ](2147483647),
		relpartbound null
	);

CREATE TABLE pg_statistic_ext (
		stxrelid OID NOT NULL,
		stxname CIDR(2147483647) NOT NULL,
		stxnamespace OID NOT NULL,
		stxowner OID NOT NULL,
		stxkeys null NOT NULL,
		stxkind ABSTIME[ ] NOT NULL,
		stxndistinct null,
		stxdependencies null
	);

CREATE TABLE games (
		id SERIAL DEFAULT nextval('games_id_seq'::regclass) NOT NULL,
		name TEXT(2147483647)
	);

CREATE TABLE pg_policy (
		polname CIDR(2147483647) NOT NULL,
		polrelid OID NOT NULL,
		polcmd BPCHAR(1) NOT NULL,
		polpermissive BOOL NOT NULL,
		polroles OID[ ],
		polqual null,
		polwithcheck null
	);

CREATE UNIQUE INDEX pg_cast_source_target_index ON pg_cast (castsource ASC, casttarget ASC);

CREATE UNIQUE INDEX pg_enum_oid_index ON pg_enum (null);

CREATE UNIQUE INDEX pg_type_oid_index ON pg_type (null);

CREATE UNIQUE INDEX pg_namespace_nspname_index ON pg_namespace (nspname ASC);

CREATE UNIQUE INDEX pg_enum_typid_label_index ON pg_enum (enumtypid ASC, enumlabel ASC);

CREATE UNIQUE INDEX pg_ts_parser_oid_index ON pg_ts_parser (null);

CREATE INDEX pg_depend_reference_index ON pg_depend (refclassid ASC, refobjid ASC, refobjsubid ASC);

CREATE UNIQUE INDEX pg_rewrite_oid_index ON pg_rewrite (null);

CREATE INDEX pg_inherits_parent_index ON pg_inherits (inhparent ASC);

CREATE UNIQUE INDEX pg_foreign_data_wrapper_name_index ON pg_foreign_data_wrapper (fdwname ASC);

CREATE UNIQUE INDEX pg_cast_oid_index ON pg_cast (null);

CREATE UNIQUE INDEX pg_collation_oid_index ON pg_collation (null);

CREATE UNIQUE INDEX pg_replication_origin_roiident_index ON pg_replication_origin (roident ASC);

CREATE UNIQUE INDEX employees_pkey ON employees (id ASC);

CREATE UNIQUE INDEX pg_ts_template_oid_index ON pg_ts_template (null);

CREATE UNIQUE INDEX pg_inherits_relid_seqno_index ON pg_inherits (inhrelid ASC, inhseqno ASC);

CREATE UNIQUE INDEX pg_attribute_relid_attnum_index ON pg_attribute (attrelid ASC, attnum ASC);

CREATE UNIQUE INDEX pg_extension_oid_index ON pg_extension (null);

CREATE UNIQUE INDEX games_pkey ON games (id ASC);

CREATE UNIQUE INDEX pg_enum_typid_sortorder_index ON pg_enum (enumtypid ASC, enumsortorder ASC);

CREATE UNIQUE INDEX pg_am_name_index ON pg_am (amname ASC);

CREATE UNIQUE INDEX pg_operator_oid_index ON pg_operator (null);

CREATE UNIQUE INDEX pg_partitioned_table_partrelid_index ON pg_partitioned_table (partrelid ASC);

CREATE UNIQUE INDEX pg_publication_rel_oid_index ON pg_publication_rel (null);

CREATE UNIQUE INDEX pg_opfamily_oid_index ON pg_opfamily (null);

CREATE UNIQUE INDEX pg_policy_oid_index ON pg_policy (null);

CREATE UNIQUE INDEX pg_conversion_name_nsp_index ON pg_conversion (conname ASC, connamespace ASC);

CREATE UNIQUE INDEX pg_largeobject_loid_pn_index ON pg_largeobject (loid ASC, pageno ASC);

CREATE UNIQUE INDEX pg_subscription_subname_index ON pg_subscription (subdbid ASC, subname ASC);

CREATE INDEX pg_statistic_ext_relid_index ON pg_statistic_ext (stxrelid ASC);

CREATE UNIQUE INDEX pg_ts_template_tmplname_index ON pg_ts_template (tmplname ASC, tmplnamespace ASC);

CREATE UNIQUE INDEX pg_rewrite_rel_rulename_index ON pg_rewrite (ev_class ASC, rulename ASC);

CREATE UNIQUE INDEX pg_language_name_index ON pg_language (lanname ASC);

CREATE UNIQUE INDEX pg_proc_oid_index ON pg_proc (null);

CREATE UNIQUE INDEX pg_largeobject_metadata_oid_index ON pg_largeobject_metadata (null);

CREATE UNIQUE INDEX pg_replication_origin_roname_index ON pg_replication_origin (roname ASC);

CREATE INDEX pg_shdepend_depender_index ON pg_shdepend (dbid ASC, classid ASC, objid ASC, objsubid ASC);

CREATE UNIQUE INDEX pg_ts_dict_oid_index ON pg_ts_dict (null);

CREATE UNIQUE INDEX pg_default_acl_role_nsp_obj_index ON pg_default_acl (defaclrole ASC, defaclnamespace ASC, defaclobjtype ASC);

CREATE UNIQUE INDEX pg_amop_fam_strat_index ON pg_amop (amopfamily ASC, amoplefttype ASC, amoprighttype ASC, amopstrategy ASC);

CREATE UNIQUE INDEX pg_user_mapping_oid_index ON pg_user_mapping (null);

CREATE UNIQUE INDEX pg_auth_members_member_role_index ON pg_auth_members (member ASC, roleid ASC);

CREATE UNIQUE INDEX pg_constraint_oid_index ON pg_constraint (null);

CREATE UNIQUE INDEX pg_type_typname_nsp_index ON pg_type (typname ASC, typnamespace ASC);

CREATE UNIQUE INDEX pg_statistic_ext_name_index ON pg_statistic_ext (stxname ASC, stxnamespace ASC);

CREATE UNIQUE INDEX pg_amproc_oid_index ON pg_amproc (null);

CREATE UNIQUE INDEX pg_event_trigger_evtname_index ON pg_event_trigger (evtname ASC);

CREATE UNIQUE INDEX pg_db_role_setting_databaseid_rol_index ON pg_db_role_setting (setdatabase ASC, setrole ASC);

CREATE UNIQUE INDEX pg_publication_oid_index ON pg_publication (null);

CREATE INDEX pg_class_tblspc_relfilenode_index ON pg_class (reltablespace ASC, relfilenode ASC);

CREATE UNIQUE INDEX pg_ts_parser_prsname_index ON pg_ts_parser (prsname ASC, prsnamespace ASC);

CREATE UNIQUE INDEX pg_ts_dict_dictname_index ON pg_ts_dict (dictname ASC, dictnamespace ASC);

CREATE UNIQUE INDEX pg_auth_members_role_member_index ON pg_auth_members (roleid ASC, member ASC);

CREATE UNIQUE INDEX pg_constraint_conrelid_contypid_conname_index ON pg_constraint (conrelid ASC, contypid ASC, conname ASC);

CREATE UNIQUE INDEX pg_shseclabel_object_index ON pg_shseclabel (objoid ASC, classoid ASC, provider ASC);

CREATE UNIQUE INDEX pg_ts_config_oid_index ON pg_ts_config (null);

CREATE INDEX pg_constraint_conparentid_index ON pg_constraint (conparentid ASC);

CREATE UNIQUE INDEX pg_class_oid_index ON pg_class (null);

CREATE UNIQUE INDEX pg_publication_rel_prrelid_prpubid_index ON pg_publication_rel (prrelid ASC, prpubid ASC);

CREATE UNIQUE INDEX pg_conversion_default_index ON pg_conversion (connamespace ASC, conforencoding ASC, contoencoding ASC);

CREATE UNIQUE INDEX pg_subscription_rel_srrelid_srsubid_index ON pg_subscription_rel (srrelid ASC, srsubid ASC);

CREATE UNIQUE INDEX pg_default_acl_oid_index ON pg_default_acl (null);

CREATE UNIQUE INDEX pg_user_mapping_user_server_index ON pg_user_mapping (umuser ASC, umserver ASC);

CREATE INDEX pg_shdepend_reference_index ON pg_shdepend (refclassid ASC, refobjid ASC);

CREATE UNIQUE INDEX pg_aggregate_fnoid_index ON pg_aggregate (aggfnoid ASC);

CREATE UNIQUE INDEX pg_foreign_table_relid_index ON pg_foreign_table (ftrelid ASC);

CREATE UNIQUE INDEX pg_am_oid_index ON pg_am (null);

CREATE UNIQUE INDEX pg_range_rngtypid_index ON pg_range (rngtypid ASC);

CREATE UNIQUE INDEX pg_event_trigger_oid_index ON pg_event_trigger (null);

CREATE UNIQUE INDEX pg_foreign_data_wrapper_oid_index ON pg_foreign_data_wrapper (null);

CREATE UNIQUE INDEX pg_publication_pubname_index ON pg_publication (pubname ASC);

CREATE UNIQUE INDEX pg_tablespace_spcname_index ON pg_tablespace (spcname ASC);

CREATE UNIQUE INDEX pg_opclass_am_name_nsp_index ON pg_opclass (opcmethod ASC, opcname ASC, opcnamespace ASC);

CREATE UNIQUE INDEX pg_ts_config_cfgname_index ON pg_ts_config (cfgname ASC, cfgnamespace ASC);

CREATE UNIQUE INDEX pg_shdescription_o_c_index ON pg_shdescription (objoid ASC, classoid ASC);

CREATE UNIQUE INDEX pg_extension_name_index ON pg_extension (extname ASC);

CREATE UNIQUE INDEX pg_sequence_seqrelid_index ON pg_sequence (seqrelid ASC);

CREATE UNIQUE INDEX pg_description_o_c_o_index ON pg_description (objoid ASC, classoid ASC, objsubid ASC);

CREATE INDEX pg_index_indrelid_index ON pg_index (indrelid ASC);

CREATE INDEX pg_trigger_tgconstraint_index ON pg_trigger (tgconstraint ASC);

CREATE UNIQUE INDEX pg_amop_opr_fam_index ON pg_amop (amopopr ASC, amoppurpose ASC, amopfamily ASC);

CREATE UNIQUE INDEX pg_foreign_server_oid_index ON pg_foreign_server (null);

CREATE UNIQUE INDEX pg_index_indexrelid_index ON pg_index (indexrelid ASC);

CREATE UNIQUE INDEX pg_authid_oid_index ON pg_authid (null);

CREATE UNIQUE INDEX pg_trigger_oid_index ON pg_trigger (null);

CREATE UNIQUE INDEX pg_transform_type_lang_index ON pg_transform (trftype ASC, trflang ASC);

CREATE UNIQUE INDEX pg_opclass_oid_index ON pg_opclass (null);

CREATE INDEX pg_depend_depender_index ON pg_depend (classid ASC, objid ASC, objsubid ASC);

CREATE UNIQUE INDEX pg_amop_oid_index ON pg_amop (null);

CREATE UNIQUE INDEX pg_seclabel_object_index ON pg_seclabel (objoid ASC, classoid ASC, objsubid ASC, provider ASC);

CREATE UNIQUE INDEX pg_proc_proname_args_nsp_index ON pg_proc (proname ASC, proargtypes ASC, pronamespace ASC);

CREATE UNIQUE INDEX pg_ts_config_map_index ON pg_ts_config_map (mapcfg ASC, maptokentype ASC, mapseqno ASC);

CREATE UNIQUE INDEX pg_tablespace_oid_index ON pg_tablespace (null);

CREATE INDEX pg_constraint_conname_nsp_index ON pg_constraint (conname ASC, connamespace ASC);

CREATE UNIQUE INDEX pg_subscription_oid_index ON pg_subscription (null);

CREATE UNIQUE INDEX pg_statistic_relid_att_inh_index ON pg_statistic (starelid ASC, staattnum ASC, stainherit ASC);

CREATE UNIQUE INDEX pg_trigger_tgrelid_tgname_index ON pg_trigger (tgrelid ASC, tgname ASC);

CREATE UNIQUE INDEX pg_policy_polrelid_polname_index ON pg_policy (polrelid ASC, polname ASC);

CREATE UNIQUE INDEX pg_collation_name_enc_nsp_index ON pg_collation (collname ASC, collencoding ASC, collnamespace ASC);

CREATE UNIQUE INDEX pg_foreign_server_name_index ON pg_foreign_server (srvname ASC);

CREATE UNIQUE INDEX pg_attribute_relid_attnam_index ON pg_attribute (attrelid ASC, attname ASC);

CREATE UNIQUE INDEX pg_transform_oid_index ON pg_transform (null);

CREATE UNIQUE INDEX pg_opfamily_am_name_nsp_index ON pg_opfamily (opfmethod ASC, opfname ASC, opfnamespace ASC);

CREATE UNIQUE INDEX pg_attrdef_adrelid_adnum_index ON pg_attrdef (adrelid ASC, adnum ASC);

CREATE UNIQUE INDEX pg_authid_rolname_index ON pg_authid (rolname ASC);

CREATE UNIQUE INDEX pg_amproc_fam_proc_index ON pg_amproc (amprocfamily ASC, amproclefttype ASC, amprocrighttype ASC, amprocnum ASC);

CREATE UNIQUE INDEX pg_pltemplate_name_index ON pg_pltemplate (tmplname ASC);

CREATE UNIQUE INDEX pg_conversion_oid_index ON pg_conversion (null);

CREATE UNIQUE INDEX pg_class_relname_nsp_index ON pg_class (relname ASC, relnamespace ASC);

CREATE UNIQUE INDEX pg_statistic_ext_oid_index ON pg_statistic_ext (null);

CREATE INDEX pg_constraint_contypid_index ON pg_constraint (contypid ASC);

CREATE UNIQUE INDEX pg_database_oid_index ON pg_database (null);

CREATE UNIQUE INDEX pg_namespace_oid_index ON pg_namespace (null);

CREATE UNIQUE INDEX pg_language_oid_index ON pg_language (null);

CREATE UNIQUE INDEX pg_operator_oprname_l_r_n_index ON pg_operator (oprname ASC, oprleft ASC, oprright ASC, oprnamespace ASC);

CREATE UNIQUE INDEX pg_init_privs_o_c_o_index ON pg_init_privs (objoid ASC, classoid ASC, objsubid ASC);

CREATE UNIQUE INDEX pg_attrdef_oid_index ON pg_attrdef (null);

CREATE UNIQUE INDEX pg_database_datname_index ON pg_database (datname ASC);

ALTER TABLE employees ADD CONSTRAINT employees_pkey PRIMARY KEY (id);

ALTER TABLE games ADD CONSTRAINT games_pkey PRIMARY KEY (id);

