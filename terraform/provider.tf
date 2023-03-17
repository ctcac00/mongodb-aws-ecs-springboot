provider "aws" {
  region  = local.aws_region
  profile = var.aws_profile
}

provider "mongodbatlas" {
  public_key  = var.atlas_public_key
  private_key = var.atlas_private_key
}
