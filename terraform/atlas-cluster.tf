resource "mongodbatlas_advanced_cluster" "cluster" {
  name         = local.cluster_name
  project_id   = var.project_id
  cluster_type = "REPLICASET"

  mongo_db_major_version = "5.0"
  backup_enabled         = true
  pit_enabled            = true

  replication_specs {
    region_configs {
      electable_specs {
        instance_size = local.atlas_size_name
        node_count    = 3
      }
      provider_name = local.provider_name
      region_name   = local.region
      priority      = 7
    }

  }
  lifecycle {
    prevent_destroy = false
  }

}
