locals {
  # Generic project prefix, to rename most components
  prefix = "carlos-castro"
  # Atlas region,https://docs.atlas.mongodb.com/reference/amazon-aws/#std-label-amazon-aws
  region = "EU_WEST_1"
  # Atlas cluster name
  cluster_name = "cluster0"
  # Atlas Pulic provider
  provider_name = "AWS"
  # Atlas size name 
  atlas_size_name = "M10"
  # IAM policy name
  aws_policy_name = "${local.prefix}-kms-policy"
  # IAM role name
  aws_role_name = "${local.prefix}-kms-role"
  # AWS Region
  aws_region = "eu-west-1"
  # AWS Rooute block
  aws_route_cidr_block = "10.11.6.0/23"
  # AWS Subnet block
  aws_subnet1_cidr_block = "10.11.6.0/24"
  tags = {
    owner       = "carlos.castro"
    purpose     = "opportunity"
    "expire-on" = "2023-04-01"
  }
}
